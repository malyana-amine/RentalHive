package com.example.RentalHive.repository;

import com.example.RentalHive.Entity.Equipment;
import com.example.RentalHive.Entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EquipmentRepository  extends JpaRepository<Equipment, Long> {
    @Query("SELECT e FROM Equipment e WHERE e.type = :type OR e.name LIKE %:name%")

    List<Equipment> findByStatusAndTypeAndName(@Param("type") Type type, @Param("name") String name);

    Optional<Equipment> findById(Long id);

}
