package com.example.RentalHive.repository;

import com.example.RentalHive.entity.Contract;
import com.example.RentalHive.entity.DemandedEquipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractRepsitory extends JpaRepository<Contract, Long> {
    @Query("SELECT c FROM Contract c " +
            "JOIN FETCH c.devis d " +
            "JOIN FETCH d.demand dm " +
            "JOIN FETCH dm.user u " +
            "JOIN FETCH dm.demandedEquipments de " +
            "JOIN FETCH de.equipment " +
            "WHERE c.id = :id")
    Contract findContractWithDetails(Long id);
}
