package com.example.RentalHive.service.imp;

import com.example.RentalHive.entity.DemandedEquipment;
import com.example.RentalHive.entity.Status;
import com.example.RentalHive.repository.DemandEquipmentRepository;
import com.example.RentalHive.service.DemandedEquipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DemandedEquipmentServiceImp implements DemandedEquipmentService {
    private final DemandEquipmentRepository repository;

    @Override
    public List<DemandedEquipment> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<DemandedEquipment> findById(Long aLong) {
        return repository.findById(aLong);
    }

    @Override
    public DemandedEquipment save(DemandedEquipment entity) {
        if (isThisDemandedEquipmentAllowed(entity)) {
            return repository.save(entity);
        } else {
            throw new RuntimeException("you cant demand this equipment: " + entity.getEquipment().getId() + " in that interval");
        }
    }

    @Override
    public DemandedEquipment update(DemandedEquipment entity) {
        if (isThisDemandedEquipmentAllowed(entity)) {
            return repository.save(entity);
        } else {
            throw new RuntimeException("you cant update this demanded equipment: " + entity.getId() + " for that interval");
        }
    }

    @Override
    public Optional<DemandedEquipment> delete(Long aLong) {
        Optional<DemandedEquipment> demandedEquipment = repository.findById(aLong);
        demandedEquipment.ifPresent(repository::delete);
        return demandedEquipment;
    }

    @Override
    public Boolean isThisDemandedEquipmentAllowed(DemandedEquipment demandedEquipment) {
        if (demandedEquipment.getEndDate().isBefore(demandedEquipment.getStartDate()) || demandedEquipment.getEndDate().isAfter(demandedEquipment.getStartDate().plusDays(30))) {
            throw new IllegalArgumentException("End date must be greater than start date and not greater than 30 days.");
        }

        return repository.isThisDemandedEquipmentAllowed(demandedEquipment.getEquipment().getId(), Status.Approved, demandedEquipment.getStartDate(), demandedEquipment.getEndDate());
    }
}
