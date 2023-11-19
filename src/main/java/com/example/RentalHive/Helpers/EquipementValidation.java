package com.example.RentalHive.Helpers;

import com.example.RentalHive.Entities.Equipment;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.Set;

public class EquipementValidation {

    public static void validateEquipmentForSave(Equipment equipment) {
        if (equipment.getName() == null) {
            throw new IllegalArgumentException("Equipment name cannot be null");
        }

        if (equipment.getType() == null) {
            throw new IllegalArgumentException("Equipment type cannot be null");
        }

        if (equipment.getPrice() != null && equipment.getPrice() < 0) {
            throw new IllegalArgumentException("Equipment price cannot be negative");
        }
    }
}
