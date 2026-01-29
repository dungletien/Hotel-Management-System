package com.example.hotelmanagement.service;

import com.example.hotelmanagement.dto.GuestRequest;
import com.example.hotelmanagement.dto.GuestResponse;
import com.example.hotelmanagement.entity.Guest;
import com.example.hotelmanagement.exception.EmailAlreadyExistsException;
import com.example.hotelmanagement.exception.GuestNotFoundException;
import com.example.hotelmanagement.repository.GuestRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GuestService {

    private final GuestRepository guestRepository;
    
    public GuestService(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    public GuestResponse createGuest(GuestRequest request) {
        if (guestRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists");
        }

        Guest guest = new Guest();
        guest.setFirstName(request.getFirstName());
        guest.setLastName(request.getLastName());
        guest.setEmail(request.getEmail());
        guest.setPhone(request.getPhone());
        guest.setAddress(request.getAddress());
        guest.setIdNumber(request.getIdNumber());
        guest.setPreferences(request.getPreferences());

        Guest saved = guestRepository.save(guest);

        return mapToResponse(saved);
    }

    public Page<GuestResponse> getAllGuests(Pageable pageable) {
        return guestRepository
                .findAllByIsDeletedFalse(pageable)
                .map(this::mapToResponse);
    }

    public GuestResponse getGuestById(Long id) {
        Guest guest = guestRepository.findById(id)
                .filter(g -> !g.getIsDeleted())
                .orElseThrow(() -> new GuestNotFoundException("Guest not found"));

        return mapToResponse(guest);
    }

    public void deleteGuest(Long id) {
        Guest guest = guestRepository.findById(id)
                .orElseThrow(() -> new GuestNotFoundException("Guest not found"));
        guest.setIsDeleted(true);
    }

    public GuestResponse updateGuest(Long id, GuestRequest request) {
        Guest guest = guestRepository.findById(id)
                .filter(g -> !g.getIsDeleted())
                .orElseThrow(() -> new GuestNotFoundException("Guest not found"));

        // Check if email is being changed and if new email already exists
        if (!guest.getEmail().equals(request.getEmail()) && 
            guestRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists");
        }

        guest.setFirstName(request.getFirstName());
        guest.setLastName(request.getLastName());
        guest.setEmail(request.getEmail());
        guest.setPhone(request.getPhone());
        guest.setAddress(request.getAddress());
        guest.setIdNumber(request.getIdNumber());
        guest.setPreferences(request.getPreferences());

        Guest updated = guestRepository.save(guest);
        return mapToResponse(updated);
    }

    public Page<GuestResponse> searchGuests(String keyword, Pageable pageable) {
        return guestRepository.searchGuests(keyword, pageable)
                .map(this::mapToResponse);
    }

    public Page<GuestResponse> searchByEmail(String email, Pageable pageable) {
        return guestRepository.findByEmailContainingIgnoreCaseAndIsDeletedFalse(email, pageable)
                .map(this::mapToResponse);
    }

    public Page<GuestResponse> searchByPhone(String phone, Pageable pageable) {
        return guestRepository.findByPhoneContainingAndIsDeletedFalse(phone, pageable)
                .map(this::mapToResponse);
    }

    public Page<GuestResponse> searchByLoyaltyPoints(Integer minPoints, Pageable pageable) {
        return guestRepository.findByLoyaltyPointsGreaterThanEqualAndIsDeletedFalse(minPoints, pageable)
                .map(this::mapToResponse);
    }

    private GuestResponse mapToResponse(Guest guest) {
        return new GuestResponse(
                guest.getId(),
                guest.getFirstName() + " " + guest.getLastName(),
                guest.getEmail(),
                guest.getPhone(),
                guest.getPreferences(),
                guest.getStayHistory(),
                guest.getLoyaltyPoints()
        );
    }
}
