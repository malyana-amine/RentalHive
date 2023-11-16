package com.example.RentalHive.Service;


import com.example.RentalHive.Entities.Equipment;
import com.example.RentalHive.Repositories.EquipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service

public interface IEquipmentService {


    public Equipment save(Equipment equipment);

    public Equipment update(Equipment equipment);

    public List<Equipment> findAll();


}
