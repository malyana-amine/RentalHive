package com.example.RentalHive.helpers;

import com.example.RentalHive.entity.Equipment;

public class EquipmentValidation {

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
