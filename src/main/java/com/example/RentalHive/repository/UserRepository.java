package com.example.RentalHive.repository;

import com.example.RentalHive.Entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<Users, Long> {
}
