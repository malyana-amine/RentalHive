package com.example.RentalHive.service.imp;

import com.example.RentalHive.entity.*;
import com.example.RentalHive.service.DemandeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;


import com.example.RentalHive.helpers.DemandValidation;

@Component
public class DemandeServiceImp implements DemandeService {

    private final DemandValidation demandValidation;

    @Autowired
    public DemandeServiceImp(DemandValidation demandValidation) {
        this.demandValidation = demandValidation;
    }
    @Override
    public Demand CreateDemand(Long userId, List<Long> equipmentIds ,  List<LocalDate> startDateList , List<LocalDate> endDateList) {

            demandValidation.validateInputs(userId, equipmentIds, startDateList, endDateList);
            Users user = demandValidation.getUserById(userId);
            Demand demand = demandValidation.createDemandForUser(user);
            demandValidation.createDemandedEquipments(demand, equipmentIds, startDateList, endDateList);
            return demand;

    }

    @Override
    public Optional<Demand> findById(Long id) {
        return demandRepository.findById(id);
    }

}
