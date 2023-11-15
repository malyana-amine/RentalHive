package com.example.RentalHive.Service;


import com.example.RentalHive.Repositories.EquipementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EquipementServiceImpl {
    private EquipementRepository equipementRepository;
}
