package com.example.RentalHive.service;

import com.example.RentalHive.DTO.UsersDTO;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends CrudService<UsersDTO, Long> {
}
