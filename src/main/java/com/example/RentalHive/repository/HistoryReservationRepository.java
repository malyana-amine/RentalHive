package com.example.RentalHive.repository;

import com.example.RentalHive.Entities.Equipment;
import com.example.RentalHive.Entities.EquipmentStatus;
import com.example.RentalHive.Entities.Reservation;
import com.example.RentalHive.Entities.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HistoryReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findReservationsByEquipmentName(String equipmentName);
}
