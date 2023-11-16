package com.example.RentalHive.Controller;


import com.example.RentalHive.Entities.Equipment;
import com.example.RentalHive.Service.IEquipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;



@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/equipements")
public class EquipmentController {


    @Qualifier("equipmentServiceImpl")
    private final IEquipmentService equipmentService;

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
}
