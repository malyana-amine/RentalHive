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

    @PutMapping("/update")
    public ResponseEntity<Equipment> updateEquipment(@RequestBody Equipment updatedEquipment) {
        try {
            Equipment result = equipmentService.updateEntireEquipment(updatedEquipment);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
