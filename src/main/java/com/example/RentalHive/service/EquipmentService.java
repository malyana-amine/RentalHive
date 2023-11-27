package com.example.RentalHive.service;

import com.example.RentalHive.entity.Equipment;
import com.example.RentalHive.entity.Type;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public interface EquipmentService {
    Equipment save(Equipment equipment, MultipartFile imageFile) throws IOException;

    List<Equipment> findByStatusTypeName(Type type, String name);

    Equipment updateEntireEquipment(Equipment equipment);


    Optional<Equipment>  findById(Long id);

    Optional<Equipment> findByIdWithTypeInfo(Long id);
}
