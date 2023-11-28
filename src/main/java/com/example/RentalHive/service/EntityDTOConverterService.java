package com.example.RentalHive.service;

import com.example.RentalHive.DTO.*;
import com.example.RentalHive.entity.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public interface EntityDTOConverterService {
     ContractDTO convertToDTO(Contract contract);
     DevisDTO convertToDTO(Devis devis);
    DemandDTO convertToDTO(Demand demand);
    UsersDTO convertUserToDTO(Optional<Users> user);
    EquipmentDTO convertEquipmentToDTO(Optional<Equipment> equipment);
    public DemandedEquipmentDTO convertToDTO(DemandedEquipment demandedEquipment);
}
