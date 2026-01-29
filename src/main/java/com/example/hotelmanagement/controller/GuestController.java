package com.example.hotelmanagement.controller;

import com.example.hotelmanagement.dto.GuestRequest;
import com.example.hotelmanagement.dto.GuestResponse;
import com.example.hotelmanagement.service.GuestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/guests")
@Tag(name = "Guest Management", description = "APIs for managing hotel guests")
public class GuestController {

    private final GuestService guestService;
    
    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }

    @PostMapping
    @Operation(summary = "Create a new guest", description = "Creates a new guest in the hotel management system")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Guest created successfully",
                content = @Content(schema = @Schema(implementation = GuestResponse.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input data"),
        @ApiResponse(responseCode = "409", description = "Email already exists")
    })
    public ResponseEntity<GuestResponse> create(@Valid @RequestBody GuestRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(guestService.createGuest(request));
    }

    @GetMapping
    @Operation(summary = "Get all guests", description = "Retrieves a paginated list of all guests")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved guests")
    })
    public Page<GuestResponse> getAll(
            @Parameter(description = "Page number (0-based)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Number of items per page") @RequestParam(defaultValue = "10") int size
    ) {
        return guestService.getAllGuests(PageRequest.of(page, size));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get guest by ID", description = "Retrieves a guest by their unique identifier")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Guest found",
                content = @Content(schema = @Schema(implementation = GuestResponse.class))),
        @ApiResponse(responseCode = "404", description = "Guest not found")
    })
    public GuestResponse getById(@Parameter(description = "Guest ID") @PathVariable Long id) {
        return guestService.getGuestById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update guest", description = "Updates an existing guest's information")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Guest updated successfully",
                content = @Content(schema = @Schema(implementation = GuestResponse.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input data"),
        @ApiResponse(responseCode = "404", description = "Guest not found"),
        @ApiResponse(responseCode = "409", description = "Email already exists")
    })
    public ResponseEntity<GuestResponse> update(
            @Parameter(description = "Guest ID") @PathVariable Long id,
            @Valid @RequestBody GuestRequest request) {
        return ResponseEntity.ok(guestService.updateGuest(id, request));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete guest", description = "Deletes a guest from the system")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Guest deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Guest not found")
    })
    public void delete(@Parameter(description = "Guest ID") @PathVariable Long id) {
        guestService.deleteGuest(id);
    }

    @GetMapping("/search")
    @Operation(summary = "Search guests by keyword", description = "Search guests by name, email, or phone number")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved search results")
    })
    public Page<GuestResponse> search(
            @Parameter(description = "Search keyword") @RequestParam String keyword,
            @Parameter(description = "Page number (0-based)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Number of items per page") @RequestParam(defaultValue = "10") int size
    ) {
        return guestService.searchGuests(keyword, PageRequest.of(page, size));
    }

    @GetMapping("/search/email")
    @Operation(summary = "Search guests by email", description = "Search guests by email address")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved search results")
    })
    public Page<GuestResponse> searchByEmail(
            @Parameter(description = "Email to search") @RequestParam String email,
            @Parameter(description = "Page number (0-based)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Number of items per page") @RequestParam(defaultValue = "10") int size
    ) {
        return guestService.searchByEmail(email, PageRequest.of(page, size));
    }

    @GetMapping("/search/phone")
    @Operation(summary = "Search guests by phone", description = "Search guests by phone number")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved search results")
    })
    public Page<GuestResponse> searchByPhone(
            @Parameter(description = "Phone number to search") @RequestParam String phone,
            @Parameter(description = "Page number (0-based)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Number of items per page") @RequestParam(defaultValue = "10") int size
    ) {
        return guestService.searchByPhone(phone, PageRequest.of(page, size));
    }

    @GetMapping("/search/loyalty-points")
    @Operation(summary = "Search guests by loyalty points", description = "Search guests with minimum loyalty points")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved search results")
    })
    public Page<GuestResponse> searchByLoyaltyPoints(
            @Parameter(description = "Minimum loyalty points") @RequestParam Integer minPoints,
            @Parameter(description = "Page number (0-based)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Number of items per page") @RequestParam(defaultValue = "10") int size
    ) {
        return guestService.searchByLoyaltyPoints(minPoints, PageRequest.of(page, size));
    }
}
