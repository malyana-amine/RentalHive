package com.example.RentalHive.repository;

import com.example.RentalHive.entity.Demand;
import com.example.RentalHive.entity.DemandedEquipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DemandEquipmentRepository extends JpaRepository<DemandedEquipment, Long> {
}
