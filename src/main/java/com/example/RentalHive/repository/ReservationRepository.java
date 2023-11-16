package com.example.RentalHive.repository;

import com.example.RentalHive.Entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository  extends JpaRepository<Reservation, Long> {
}
