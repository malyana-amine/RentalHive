package com.example.RentalHive.service;

import com.example.RentalHive.Entities.Reservation;

import java.util.List;

public interface ReservationService {
    public Reservation save(Reservation reservation);
    public List<Reservation> getAllReservation();
}
