package com.example.RentalHive.web.rest;


import com.example.RentalHive.Entities.Equipment;
import com.example.RentalHive.Exceptions.EquipmentAlreadyExistsException;
import com.example.RentalHive.Service.EquipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/equipments")
public class EquipmentRest {
    private final EquipmentService equipmentService;

    @PostMapping("/add")
    public ResponseEntity<Equipment> addEquipment(@RequestBody Equipment equipment) {
        try {
            Equipment savedEquipment = equipmentService.save(equipment);

            return new ResponseEntity<>(savedEquipment, HttpStatus.CREATED);

        } catch (IllegalArgumentException e) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Equipment> updateEquipment(
            @PathVariable Long id,
            @RequestBody Equipment updatedAttributes) {
        try {
            Optional<Equipment> optionalExistingEquipment = equipmentService.findById(id);

            if (!optionalExistingEquipment.isPresent()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            Equipment existingEquipment = optionalExistingEquipment.get();

            if (updatedAttributes.getName() != null) {
                existingEquipment.setName(updatedAttributes.getName());
            }

            Equipment updatedEquipment = equipmentService.update(existingEquipment);

            return new ResponseEntity<>(updatedEquipment, HttpStatus.OK);

        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
