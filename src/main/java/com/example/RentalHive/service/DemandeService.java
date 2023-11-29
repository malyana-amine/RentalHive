package com.example.RentalHive.service;

import com.example.RentalHive.entity.Demand;
import com.example.RentalHive.entity.DemandedEquipment;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public interface DemandeService  {

    Demand CreateDemand(Long userId, List<Long> equipmentIds,List<LocalDate> startDateList , List<LocalDate> endDateList);
    Optional<Demand> findById(Long id);
}
