package com.example.RentalHive.repository;

import com.example.RentalHive.Entities.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentRepository  extends JpaRepository<Equipment, Long> {
}
