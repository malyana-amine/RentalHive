package com.example.RentalHive.service;

import com.example.RentalHive.entity.Users;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends CrudService<Users, Long> {
}
