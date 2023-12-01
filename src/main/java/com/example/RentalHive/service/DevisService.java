package com.example.RentalHive.service;

import com.example.RentalHive.DTO.DemandDTO;
import com.example.RentalHive.DTO.DevisDTO;
import com.example.RentalHive.entity.Demand;
import com.example.RentalHive.entity.Devis;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DevisService {
    Devis generateDevis(Long id);
    List<DevisDTO> findAll();
    void verifyAndUpdateStatus();
    List<Demand> findApprovedDemand();
    void accepteDevis(Long id);
}