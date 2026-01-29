package com.example.hotelmanagement.exception;

public class GuestNotFoundException extends RuntimeException {
    public GuestNotFoundException(String msg) {
        super(msg);
    }
}
