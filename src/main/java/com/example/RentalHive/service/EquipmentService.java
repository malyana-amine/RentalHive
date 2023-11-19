package com.example.RentalHive.Service;

import com.example.RentalHive.Entities.Equipment;
import com.example.RentalHive.Entities.EquipmentStatus;
import com.example.RentalHive.Entities.Type;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface EquipmentService {


    Equipment save(Equipment equipment);


    List<Equipment> findByStatusTypeName(EquipmentStatus status, Type type, String name);

    Equipment update(Equipment equipment);

    Optional<Equipment>  findById(Long id);
}
