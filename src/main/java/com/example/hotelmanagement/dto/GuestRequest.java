package com.example.hotelmanagement.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Guest creation/update request")
public class GuestRequest {

    @Schema(description = "Guest's first name", example = "John", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    private String firstName;

    @Schema(description = "Guest's last name", example = "Doe", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    private String lastName;

    @Schema(description = "Guest's email address", example = "john.doe@example.com", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    @Email
    private String email;

    @Schema(description = "Guest's phone number", example = "+84901234567", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    private String phone;

    @Schema(description = "Guest's address", example = "123 Main St, Ho Chi Minh City")
    private String address;
    
    @Schema(description = "Guest's ID number (passport or national ID)", example = "123456789")
    private String idNumber;

    @Schema(description = "Guest's preferences (room type, floor preference, etc.)", example = "Non-smoking, High floor, King bed")
    private String preferences;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getPreferences() {
        return preferences;
    }

    public void setPreferences(String preferences) {
        this.preferences = preferences;
    }
}
