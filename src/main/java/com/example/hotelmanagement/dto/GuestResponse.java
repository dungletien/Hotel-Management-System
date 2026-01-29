package com.example.hotelmanagement.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Guest response data")
public class GuestResponse {
    
    @Schema(description = "Guest's unique identifier", example = "1")
    private Long id;
    
    @Schema(description = "Guest's full name", example = "John Doe")
    private String fullName;
    
    @Schema(description = "Guest's email address", example = "john.doe@example.com")
    private String email;
    
    @Schema(description = "Guest's phone number", example = "+84901234567")
    private String phone;
    
    @Schema(description = "Guest's preferences", example = "Non-smoking, High floor")
    private String preferences;
    
    @Schema(description = "Guest's stay history", example = "2023-01-15: Room 101, 2023-06-20: Room 205")
    private String stayHistory;
    
    @Schema(description = "Guest's loyalty points", example = "0")
    private Integer loyaltyPoints;

    public GuestResponse(Long id, String fullName, String email, String phone, String preferences, String stayHistory, Integer loyaltyPoints) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.preferences = preferences;
        this.stayHistory = stayHistory;
        this.loyaltyPoints = loyaltyPoints;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public String getPreferences() {
        return preferences;
    }

    public void setPreferences(String preferences) {
        this.preferences = preferences;
    }

    public String getStayHistory() {
        return stayHistory;
    }

    public void setStayHistory(String stayHistory) {
        this.stayHistory = stayHistory;
    }

    public Integer getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(Integer loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }
}
