package com.example.RentalHive.web.rest;


import com.example.RentalHive.service.EquipmentService;
import com.example.RentalHive.entity.Equipment;

import com.example.RentalHive.entity.Type;
import com.example.RentalHive.service.TypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/equipments")
public class EquipmentRest {
    private final EquipmentService equipmentService;
    private final TypeService typeService;

    @GetMapping("")
    public ResponseEntity<List<Equipment>> getAllEquipments() {
        try {
            List<Equipment> equipments = equipmentService.findAll();
            return ResponseEntity.ok(equipments);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addEquipment( @RequestParam(required = false) String name,
                                                   @RequestParam(required = false) Double price,
                                                   @RequestParam(required = false) Long type_id,
                                                   @RequestParam(required = false) MultipartFile imageFile) {

        try {

            Equipment equipment = Equipment.builder()
                    .name(name)
                    .price(price)
                    .type(Type.builder().id(type_id).build())
                    .build();

            Equipment savedEquipment = equipmentService.save(equipment, imageFile);

            return new ResponseEntity<>(savedEquipment, HttpStatus.CREATED);

        } catch (IllegalArgumentException e) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/update")
    public ResponseEntity<Equipment> updateEquipment( @RequestParam Long equipmentId,
                                                      @RequestParam(required = false) String name,
                                                      @RequestParam(required = false) Double price,
                                                      @RequestParam(required = false) MultipartFile imageFile) {

        try {
            Equipment existingEquipment = equipmentService.findById(equipmentId)
                    .orElseThrow(() -> new IllegalArgumentException("Equipment not found"));

            if (name != null) {
                existingEquipment.setName(name);
            }

            if (price != null) {
                existingEquipment.setPrice(price);
            }
            if (imageFile != null && !imageFile.isEmpty()) {
                String filePath = "imagesSpring/" + imageFile.getOriginalFilename();
                File file = new File(filePath);

                imageFile.transferTo(file.getParentFile());

                existingEquipment.setImage(filePath);
            }

            Equipment updatedEquipment = equipmentService.updateEntireEquipment(existingEquipment);
            return new ResponseEntity<>(updatedEquipment, HttpStatus.OK);

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
