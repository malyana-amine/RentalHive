package com.example.RentalHive.service.imp;

import com.example.RentalHive.DTO.ContractDTO;
import com.example.RentalHive.DTO.DevisDTO;
import com.example.RentalHive.service.EntityDTOConverterService;
import com.example.RentalHive.entity.Contract;
import com.example.RentalHive.entity.Devis;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("entityDTOConverterService")
public class EntityDTOConverterServiceImp implements EntityDTOConverterService {


    private final ModelMapper modelMapper;

    public EntityDTOConverterServiceImp(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public ContractDTO convertToDTO(Contract contract) {
        return modelMapper.map(contract, ContractDTO.class);
    }

    @Override
    public DevisDTO convertToDTO(Devis devis) {
        return modelMapper.map(devis, DevisDTO.class);
    }
}
