package com.example.RentalHive.repository;

import com.example.RentalHive.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<Users, Long> {

}
