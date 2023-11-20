package com.example.RentalHive.Helpers;


import com.example.RentalHive.Entities.Equipment;

import java.util.Optional;

public class EquipmentUpdater {

    public static Equipment updateEquipment(Optional<Equipment> optionalExistingEquipment, Equipment updatedEquipment) {
        if (optionalExistingEquipment.isPresent()) {
            Equipment existingEquipment = optionalExistingEquipment.get();

            existingEquipment.setName(updatedEquipment.getName());
            existingEquipment.setStatus(updatedEquipment.getStatus());
            existingEquipment.setPrice(updatedEquipment.getPrice());

            if (updatedEquipment.getType() != null) {
                existingEquipment.setType(updatedEquipment.getType());
            }

            return existingEquipment;
        } else {
            throw new IllegalArgumentException("Equipment not found with ID: " + updatedEquipment.getId());
        }
    }
}