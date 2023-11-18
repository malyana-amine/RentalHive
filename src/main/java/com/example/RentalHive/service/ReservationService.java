package com.example.RentalHive.service;

import com.example.RentalHive.Entities.Reservation;
import org.springframework.stereotype.Service;

@Service
public interface ReservationService {

    void save(Reservation reservation , Long equi ,Long userid);
}
