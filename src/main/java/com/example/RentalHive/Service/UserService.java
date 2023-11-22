package com.example.RentalHive.Service;

import com.example.RentalHive.Entity.Users;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends CrudService<Users, Long> {
}
