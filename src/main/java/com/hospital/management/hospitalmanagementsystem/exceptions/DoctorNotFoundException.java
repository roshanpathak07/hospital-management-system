package com.hospital.management.hospitalmanagementsystem.exceptions;

public class DoctorNotFoundException extends RuntimeException{

    public DoctorNotFoundException(String message) {
        super(message);
    }
}
