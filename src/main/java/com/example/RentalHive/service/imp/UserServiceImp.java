package com.example.RentalHive.service.imp;

import com.example.RentalHive.DTO.UsersDTO;
import com.example.RentalHive.entity.Users;
import com.example.RentalHive.repository.UserRepository;
import com.example.RentalHive.service.RoleService;
import com.example.RentalHive.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserServiceImp implements UserService {
    private final UserRepository repository;
    private final RoleService roleService;

    private final ModelMapper mapper;

    @Override
    public List<UsersDTO> findAll() {
        return repository
                .findAll()
                .stream()
                .map(user -> mapper.map(user, UsersDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UsersDTO> findById(Long aLong) {
        return repository
                .findById(aLong)
                .map(user -> mapper.map(user, UsersDTO.class));
    }

    @Override
    public UsersDTO save(UsersDTO entityDTO) {
        Users user = mapper.map(entityDTO, Users.class);

        roleService
                .findById(user.getRole().getId())
                .orElseThrow(() -> new RuntimeException("role not found"));

        Users saved = repository.save(user);
        return mapper.map(saved, UsersDTO.class);
    }

    @Override
    public UsersDTO update(UsersDTO entityDTO) {
        return this.save(entityDTO);
    }

    @Override
    public Optional<UsersDTO> delete(Long aLong) {
        Optional<Users> user = repository.findById(aLong);
        if (user.isPresent()) {
            repository.delete(user.get());
            return Optional.of(mapper.map(user.get(), UsersDTO.class));
        } else {
            return Optional.empty();
        }
    }
}
