package com.hospital.management.hospitalmanagementsystem.exceptions;

public class BillNotFoundException extends RuntimeException{

    public BillNotFoundException(String message) {
        super(message);
    }
}
