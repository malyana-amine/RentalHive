package com.example.RentalHive.repository;

import com.example.RentalHive.Entities.Equipment;
import com.example.RentalHive.Entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository  extends JpaRepository<Reservation, Long>

{

    @Query("SELECT r FROM Reservation r " +
            "WHERE r.equipment = :equipment " +
            "AND (:startDate BETWEEN r.startDate AND r.endDate OR :endDate BETWEEN r.startDate AND r.endDate)")
    List<Reservation> findOverlappingReservations(@Param("equipment") Equipment equipment,
                                                  @Param("startDate") LocalDate startDate,
                                                  @Param("endDate") LocalDate endDate);

    @Query("SELECT r FROM Reservation r " +
            "WHERE r.equipment = :equipment " +
            "AND (:endDate > r.startDate AND :startDate < r.endDate)")
    List<Reservation> findConflictingReservations(@Param("equipment") Equipment equipment,
                                                  @Param("startDate") LocalDate startDate,
                                                  @Param("endDate") LocalDate endDate);
}

