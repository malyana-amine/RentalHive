package com.example.RentalHive.repository;

import com.example.RentalHive.entity.Demand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemandeRepository extends JpaRepository<Demand, Long> {
}
