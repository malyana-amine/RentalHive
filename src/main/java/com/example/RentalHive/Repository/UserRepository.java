package com.example.RentalHive.Repository;

import com.example.RentalHive.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<Users, Long> {

}
