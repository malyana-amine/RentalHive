package com.example.RentalHive.service;

import com.example.RentalHive.DTO.DemandDTO;
import com.example.RentalHive.entity.Demand;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public interface DemandeService  {

    Demand CreateDemand(Long userId, List<Long> equipmentIds,List<LocalDate> startDateList , List<LocalDate> endDateList);
    List<DemandDTO> findAll();
    Optional<DemandDTO> findById(Long aLong);
    DemandDTO update(DemandDTO entityDTO);
}
