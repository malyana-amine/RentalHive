package com.example.RentalHive.service;

import com.example.RentalHive.entity.Demand;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface DemandeService  {

    Demand CreateDemand(Long userId, List<Long> equipmentIds);
}
