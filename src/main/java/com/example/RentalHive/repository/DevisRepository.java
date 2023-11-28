package com.example.RentalHive.repository;

import com.example.RentalHive.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DevisRepository extends JpaRepository<Devis, Long> {

    @Query("SELECT d FROM Demand d WHERE d.status = :status")
    Demand getDemandByStatus(@Param("status") Status status);





    // Custom query to find all pending devis that have expired
//    @Query("SELECT d FROM Devis d WHERE d.statut = 'En attente' AND d.dateExpiration < :currentDate")
//    List<Devis> findExpiredPendingDevis(@Param("currentDate") Date currentDate);
}
