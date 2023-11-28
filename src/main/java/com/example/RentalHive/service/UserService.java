package com.example.RentalHive.Service;

import com.example.RentalHive.entity.Users;
import com.example.RentalHive.service.CrudService;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends CrudService<Users, Long> {
}
