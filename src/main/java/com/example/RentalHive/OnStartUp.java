package com.example.RentalHive;

import com.example.RentalHive.Entities.Equipment;
import com.example.RentalHive.Entities.EquipmentStatus;
import com.example.RentalHive.Entities.Type;
import com.example.RentalHive.repository.EquipmentRepository;
import com.example.RentalHive.repository.TypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OnStartUp implements CommandLineRunner {
    private final EquipmentRepository equipmentRepository;
    private final TypeRepository typeRepository;
    @Override
    public void run(String... args) throws Exception {
        List<Equipment> equipments = new ArrayList<>();
        Type type = typeRepository.findById(1L).orElseThrow();
        equipments.add(Equipment.builder()
                        .name("trax")
                        .price(112.1)
                        .type(type)
                        .status(EquipmentStatus.AVAILABLE)
                .build());
        equipments.add(Equipment.builder()
                        .name("car")
                        .price(212.1)
                        .type(type)
                        .status(EquipmentStatus.AVAILABLE)
                .build());

        //equipmentRepository.saveAll(equipments);
    }
}
