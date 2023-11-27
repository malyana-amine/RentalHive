package com.example.RentalHive.service;

import com.example.RentalHive.entity.Demand;
import com.example.RentalHive.entity.Devis;
import org.springframework.stereotype.Service;

@Service
public interface DevisService {
    Devis generateDevis(Devis devis);
}