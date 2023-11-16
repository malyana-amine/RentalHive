package com.example.RentalHive.Service.Impl;


import com.example.RentalHive.Entities.Equipment;
import com.example.RentalHive.Repositories.EquipmentRepository;
import com.example.RentalHive.Service.IEquipmentService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class EquipmentServiceImpl implements IEquipmentService {
    private EquipmentRepository equipmentRepository;

    @Autowired
    public EquipmentServiceImpl(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
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

        return equipmentRepository.save(equipment);
    }

    @Override
    public Equipment update(Equipment equipment) {

        return null;
    }

    @Override
    public List<Equipment> findAll() {

        return null;
    }
}
