package com.example.RentalHive.repository;

import com.example.RentalHive.entity.Contract;
import com.example.RentalHive.entity.DemandedEquipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractRepsitory extends JpaRepository<Contract, Long> {
}
