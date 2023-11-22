package com.example.RentalHive.Service;

import com.example.RentalHive.Entity.Equipment;
import com.example.RentalHive.Entity.Type;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface EquipmentService {


    Equipment save(Equipment equipment);

    List<Equipment> findByStatusTypeName(Type type, String name);

    Equipment updateEntireEquipment(Equipment equipment);

    Optional<Equipment>  findById(Long id);
}
