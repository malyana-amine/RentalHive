package com.example.RentalHive.service;

import com.example.RentalHive.Entities.Equipment;
import com.example.RentalHive.Entities.EquipmentStatus;
import com.example.RentalHive.Entities.Type;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EquipmentService {
    List<Equipment> findByStatusTypeName(EquipmentStatus status, Type type, String name);
}