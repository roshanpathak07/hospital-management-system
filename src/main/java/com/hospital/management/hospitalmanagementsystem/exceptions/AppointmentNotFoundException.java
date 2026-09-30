package com.hospital.management.hospitalmanagementsystem.exceptions;

public class AppointmentNotFoundException extends RuntimeException{

    public AppointmentNotFoundException(String message) {
        super(message);
    }
}
