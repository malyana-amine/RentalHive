package com.example.RentalHive.service.imp;

import com.example.RentalHive.DTO.*;
import com.example.RentalHive.entity.*;
import com.example.RentalHive.service.EntityDTOConverterService;
import com.example.RentalHive.service.EquipmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Qualifier("entityDTOConverterService")
public class EntityDTOConverterServiceImp implements EntityDTOConverterService {


    private final ModelMapper modelMapper;

    private final  EquipmentService equipmentService;
    public EntityDTOConverterServiceImp(ModelMapper modelMapper , EquipmentService equipmentService) {
        this.modelMapper = modelMapper;
        this.equipmentService = equipmentService;
    }

    @Override
    public ContractDTO convertToDTO(Contract contract) {
        return modelMapper.map(contract, ContractDTO.class);
    }

    @Override
    public DevisDTO convertToDTO(Devis devis) {
        return modelMapper.map(devis, DevisDTO.class);
    }

    @Override
    public DemandDTO convertToDTO(Demand demand) {

        DemandDTO demandDTO = modelMapper.map(demand, DemandDTO.class);
        List<DemandedEquipmentDTO> demandedEquipmentDTOs = convertDemandedEquipmentsToDTOs(demand.getDemandedEquipments());
        demandDTO.setDemandedEquipments(demandedEquipmentDTOs);
        return demandDTO;
    }

    @Override
    public DemandedEquipmentDTO convertToDTO(DemandedEquipment demandedEquipment) {
        return  modelMapper.map(demandedEquipment, DemandedEquipmentDTO.class);
    }
    private List<DemandedEquipmentDTO> convertDemandedEquipmentsToDTOs(List<DemandedEquipment> demandedEquipments) {

        if (demandedEquipments == null) {
            return Collections.emptyList();
        }
        return demandedEquipments.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public UsersDTO convertUserToDTO(Optional<Users> user) {
        return user.map(u -> modelMapper.map(u, UsersDTO.class)).orElse(null);
    }

    public EquipmentDTO convertEquipmentToDTO(Optional<Equipment> equipment) {
        return equipment.map(e -> modelMapper.map(e, EquipmentDTO.class)).orElse(null);
    }

    public List<DemandDTO> convertToDTOList(List<Demand> demands) {
        return demands.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }



}
