package com.example.RentalHive.Service.imp;

import com.example.RentalHive.Entities.Role;
import com.example.RentalHive.repository.RoleRepository;
import com.example.RentalHive.Service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RoleServiceImp implements RoleService {
    private final RoleRepository repository;

    @Override
    public List<Role> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Role> findById(Long aLong) {
        return repository.findById(aLong);
    }

    @Override
    public Role save(Role entity) {
        return repository.save(entity);
    }

    @Override
    public Role update(Role entity) {
        return repository.save(entity);
    }

    @Override
    public Optional<Role> delete(Long aLong) {
        Optional<Role> role = repository.findById(aLong);
        role.ifPresent(repository::delete);
        return role;
    }
}
