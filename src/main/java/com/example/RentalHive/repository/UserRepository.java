package com.example.RentalHive.repository;

import org.h2.engine.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User, Long> {
}
