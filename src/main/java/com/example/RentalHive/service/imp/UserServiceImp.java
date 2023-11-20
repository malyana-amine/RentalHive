package com.example.RentalHive.Service.imp;

import com.example.RentalHive.Entities.Users;
import com.example.RentalHive.repository.UserRepository;
import com.example.RentalHive.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserServiceImp implements UserService {
    private final UserRepository repository;

    @Override
    public List<Users> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Users> findById(Long aLong) {
        return repository.findById(aLong);
    }

    @Override
    public Users save(Users entity) {
        return repository.save(entity);
    }

    @Override
    public Users update(Users entity) {
        return repository.save(entity);
    }

    @Override
    public Optional<Users> delete(Long aLong) {
        Optional<Users> user = repository.findById(aLong);
        user.ifPresent(repository::delete);
        return user;
    }
}
