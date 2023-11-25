package com.example.RentalHive.repository;

import com.example.RentalHive.entity.DemandedEquipment;
import com.example.RentalHive.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface DemandEquipmentRepository extends JpaRepository<DemandedEquipment, Long> {
    @Query("SELECT COUNT(de) = 0 " +
            "FROM DemandedEquipment de " +
            "WHERE current date <= de.endDate " +
            "AND de.equipment.id = :equipmentId " +
            "AND de.demand.status = :status " +
            "AND (de.startDate <= :endDate AND de.endDate >= :startDate) ")
    boolean isThisDemandedEquipmentAllowed(
            @Param("equipmentId") Long equipmentId,
            @Param("status") Status status,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );
}
