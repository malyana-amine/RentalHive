package com.example.RentalHive.Service.imp;

import com.example.RentalHive.Entity.Type;
import com.example.RentalHive.Repository.TypeRepository;
import com.example.RentalHive.Service.TypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TypeServiceImp implements TypeService {
    private final TypeRepository repository;

    @Override
    public List<Type> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Type> findById(Long aLong) {
        return repository.findById(aLong);
    }

    @Override
    public Type save(Type entity) {
        return repository.save(entity);
    }

    @Override
    public Type update(Type entity) {
        return repository.save(entity);
    }

    @Override
    public Optional<Type> delete(Long aLong) {
        Optional<Type> type = repository.findById(aLong);
        type.ifPresent(repository::delete);
        return type;
    }
}