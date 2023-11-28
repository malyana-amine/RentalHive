package com.example.RentalHive.service.imp;

import com.example.RentalHive.DTO.RoleDTO;
import com.example.RentalHive.entity.Role;
import com.example.RentalHive.repository.RoleRepository;
import com.example.RentalHive.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RoleServiceImp implements RoleService {
    private final RoleRepository repository;

    private final ModelMapper mapper;

    @Override
    public List<RoleDTO> findAll() {
        return repository
                .findAll()
                .stream()
                .map(role -> mapper.map(role, RoleDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<RoleDTO> findById(Long aLong) {
        return repository
                .findById(aLong)
                .map(role -> mapper.map(role, RoleDTO.class));
    }

    @Override
    public RoleDTO save(RoleDTO entityDTO) {
        Role role = mapper.map(entityDTO, Role.class);
        Role saved = repository.save(role);
        return mapper.map(saved, RoleDTO.class);
    }

    @Override
    public RoleDTO update(RoleDTO entityDTO) {
        return this.save(entityDTO);
    }

    @Override
    public Optional<RoleDTO> delete(Long aLong) {
        Optional<Role> role = repository.findById(aLong);
        if (role.isPresent()) {
            repository.delete(role.get());
            return Optional.of(mapper.map(role.get(), RoleDTO.class));
        } else {
            return Optional.empty();
        }
    }
}
