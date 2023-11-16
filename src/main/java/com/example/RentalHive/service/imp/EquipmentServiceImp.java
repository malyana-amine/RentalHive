package com.example.RentalHive.Service.imp;

import com.example.RentalHive.Entities.Equipment;
import com.example.RentalHive.Entities.StatusEnum;
import com.example.RentalHive.Entities.Type;
import com.example.RentalHive.repository.EquipmentRepository;
import com.example.RentalHive.Service.EquipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import com.example.RentalHive.Exceptions.EquipmentAlreadyExistsException;
import java.util.List;

@Component @RequiredArgsConstructor
public class EquipmentServiceImp implements EquipmentService {
    private final EquipmentRepository repository;
    @Override
    public List<Equipment> findByStatusTypeName(StatusEnum status, Type type, String name) {
//        return repository.findByStatusAndTypeAndName(status, type, name);
        return null;
    }

    @Override
     public Equipment save(Equipment equipment){
        if (equipment.getName() == null) {
            throw new IllegalArgumentException("Equipment name cannot be null");
        }

        if (equipment.getType() == null) {
            throw new IllegalArgumentException("Equipment type cannot be null");
        }

        if (equipment.getPrice() != null && equipment.getPrice() < 0) {
            throw new IllegalArgumentException("Equipment price cannot be negative");
        }

        return repository.save(equipment);
    }
}
