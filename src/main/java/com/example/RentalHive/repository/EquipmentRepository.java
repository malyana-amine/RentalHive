package com.example.RentalHive.repository;

import com.example.RentalHive.entity.Equipment;
import com.example.RentalHive.entity.Type;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EquipmentRepository  extends JpaRepository<Equipment, Long> {
    @Query("SELECT e FROM Equipment e WHERE e.type = :type OR e.name LIKE %:name%")
    List<Equipment> findByTypeAndName(@Param("type") Type type, @Param("name") String name);


    Optional<Equipment> findById(Long id);

    @Query("SELECT e FROM Equipment e LEFT JOIN FETCH e.type WHERE e.id = :id")
    Optional<Equipment> findByIdWithTypeInfo(@Param("id") Long id);
}
