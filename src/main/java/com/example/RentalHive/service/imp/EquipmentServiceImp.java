package com.example.RentalHive.Service.imp;

import com.example.RentalHive.Entities.Equipment;
import com.example.RentalHive.Entities.EquipmentStatus;
import com.example.RentalHive.Entities.Type;
import com.example.RentalHive.repository.EquipmentRepository;
import com.example.RentalHive.Service.EquipmentService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import com.example.RentalHive.Exceptions.EquipmentAlreadyExistsException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component @RequiredArgsConstructor
public class EquipmentServiceImp implements EquipmentService {
    private final EquipmentRepository repository;
    @Override
    public List<Equipment> findByStatusTypeName(EquipmentStatus status, Type type, String name) {
//        return repository.findByStatusAndTypeAndName(status, type, name);
        return null;
    }

    @Override
     public Equipment save(Equipment equipment){
        if (equipment.getName() == null) {
            throw new IllegalArgumentException("Equipment name cannot be null");
        }

        if (equipment.getType() == null) {
            throw new IllegalArgumentException("Equipment type cannot be null");
        }

        if (equipment.getPrice() != null && equipment.getPrice() < 0) {
            throw new IllegalArgumentException("Equipment price cannot be negative");
        }

        return repository.save(equipment);
    }

    private void validateEquipment(Equipment equipment) {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<Equipment>> violations = validator.validate(equipment);

        if (!violations.isEmpty()) {
            StringBuilder errorMessage = new StringBuilder("Invalid Equipment object:");
            for (ConstraintViolation<Equipment> violation : violations) {
                errorMessage.append(" ").append(violation.getMessage());
            }
            throw new IllegalArgumentException(errorMessage.toString());
        }
    }

  @Override
    public Equipment update(Equipment equipment){

        validateEquipment(equipment);

        return repository.save(equipment);
    }

    @Override

    public Optional<Equipment> findById(Long id){
        return repository.findById(id);
    }



}
