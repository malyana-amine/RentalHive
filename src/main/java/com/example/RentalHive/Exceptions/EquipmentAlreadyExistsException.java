package com.example.RentalHive.Exceptions;


public class EquipmentAlreadyExistsException extends RuntimeException {

    public EquipmentAlreadyExistsException(String message) {
        super(message);
    }
}
