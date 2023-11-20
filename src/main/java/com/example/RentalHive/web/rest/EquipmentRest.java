package com.example.RentalHive.web.rest;


import com.example.RentalHive.Entities.Equipment;

import com.example.RentalHive.Entities.EquipmentStatus;
import com.example.RentalHive.Entities.Type;
import com.example.RentalHive.Service.EquipmentService;
import com.example.RentalHive.service.TypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/equipments")
public class EquipmentRest {
    private final EquipmentService equipmentService;
    private final TypeService typeService;

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

    @GetMapping("/{id}")
    public ResponseEntity<?> getEquipmentById(@PathVariable Long id) {
        try {
            Optional<Equipment> optionalEquipment = equipmentService.findById(id);

            if (optionalEquipment.isPresent()) {
                Equipment equipment = optionalEquipment.get();
                return ResponseEntity.ok(equipment);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<Equipment>> filterEquipments(@RequestParam(required = false) String status,
                                                            @RequestParam(required = false, defaultValue = "-1") Long type,
                                                            @RequestParam(required = false) String name){

        EquipmentStatus equipmentStatus = Arrays.stream(EquipmentStatus.values()).anyMatch(s -> s.name().equals(status)) ? EquipmentStatus.valueOf(status) : null;
        Type equipmentType = typeService.findById(type).orElse(null);

        List<Equipment> list = equipmentService.findByStatusTypeName(equipmentStatus, equipmentType, name);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }
}
