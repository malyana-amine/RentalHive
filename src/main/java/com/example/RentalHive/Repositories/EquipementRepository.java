package com.example.RentalHive.Repositories;

import com.example.RentalHive.Entities.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EquipementRepository extends JpaRepository<Equipment, Long> {

}
