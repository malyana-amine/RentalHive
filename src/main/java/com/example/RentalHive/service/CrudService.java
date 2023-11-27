package com.example.RentalHive.service;

import java.util.List;
import java.util.Optional;

public interface CrudService<T, ID> {
    List<T> findAll();

    Optional<T> findById(ID id);

    T save(T entity);

    T update(T entity);

    Optional<T> delete(ID id);
}
