package com.example.RentalHive.Repository;

import com.example.RentalHive.Entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends JpaRepository<Type, Long> {
}
