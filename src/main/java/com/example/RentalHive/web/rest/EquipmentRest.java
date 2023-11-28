package com.example.RentalHive.web.rest;


import com.example.RentalHive.Service.EquipmentService;
import com.example.RentalHive.entity.Equipment;

import com.example.RentalHive.entity.Type;
import com.example.RentalHive.service.TypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/equipments")
public class EquipmentRest {
    private final EquipmentService equipmentService;
    private final TypeService typeService;

    @PostMapping("/add")
    public ResponseEntity<Equipment> addEquipment( @RequestParam(required = false) String name,
                                                   @RequestParam(required = false) Double price,
                                                   @RequestParam(required = false) Long typeId,  // Assuming type is identified by an ID
                                                   @RequestParam(required = false) MultipartFile imageFile) {

        try {
            // Create an Equipment object from the request parameters
            Equipment equipment = Equipment.builder()
                    .name(name)
                    .price(price)
                    .type(Type.builder().id(typeId).build())
                    .build();

            Equipment savedEquipment = equipmentService.save(equipment, imageFile);

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
    public ResponseEntity<List<Equipment>> filterEquipments(@RequestParam(required = false, defaultValue = "-1") Long type,
                                                            @RequestParam(required = false) String name){

        Type equipmentType = typeService.findById(type).orElse(null);

        List<Equipment> list = equipmentService.findByStatusTypeName(equipmentType, name);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }
}
