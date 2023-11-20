package com.example.RentalHive.Service;

import com.example.RentalHive.Entities.Type;
import org.springframework.stereotype.Service;
import com.example.RentalHive.Service.CrudService;


@Service
public interface TypeService extends CrudService<Type, Long> {
}
