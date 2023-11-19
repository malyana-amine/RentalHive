package com.example.RentalHive.Helpers;

import com.example.RentalHive.Entities.Equipment;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.Set;

public class EquipementValidation {
    public static void validateEquipment(Equipment equipment) {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<Equipment>> violations = validator.validate(equipment);

        if (!violations.isEmpty()) {
            StringBuilder errorMessage = new StringBuilder("Invalid Equipment object:");
            for (ConstraintViolation<Equipment> violation : violations) {
                errorMessage.append(" ").append(violation.getMessage());
            }
            throw new IllegalArgumentException(errorMessage.toString());
        }
    }
}
