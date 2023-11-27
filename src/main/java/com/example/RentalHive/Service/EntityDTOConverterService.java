package com.example.RentalHive.Service;

import com.example.RentalHive.DTO.ContractDTO;
import com.example.RentalHive.DTO.DevisDTO;
import com.example.RentalHive.entity.Contract;
import com.example.RentalHive.entity.Devis;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service
public interface EntityDTOConverterService {
    public ContractDTO convertToDTO(Contract contract);
    public DevisDTO convertToDTO(Devis devis);
}
