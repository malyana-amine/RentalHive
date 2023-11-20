package com.example.RentalHive.Service;

import com.example.RentalHive.Entities.Role;
import org.springframework.stereotype.Service;
import com.example.RentalHive.Service.CrudService;

@Service
public interface RoleService extends CrudService<Role, Long> {
}
