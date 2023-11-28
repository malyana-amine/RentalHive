package com.example.RentalHive.helpers;

import com.example.RentalHive.entity.*;
import com.example.RentalHive.repository.DemandEquipmentRepository;
import com.example.RentalHive.repository.DemandeRepository;
import com.example.RentalHive.repository.EquipmentRepository;
import com.example.RentalHive.repository.UserRepository;
import com.example.RentalHive.service.DemandedEquipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;


@Component
@RequiredArgsConstructor
public class DemandValidation {

    private final DemandeRepository demandRepository;
    private final UserRepository userRepository;
    private final EquipmentRepository equipmentRepository;
    private final DemandEquipmentRepository demandEquipmentRepository;
    private final DemandedEquipmentService demandedEquipmentService;


    public void validateInputs(Long userId, List<Long> equipmentIds, List<LocalDate> startDateList, List<LocalDate> endDateList) {
        if (userId == null) {
            throw new IllegalArgumentException("User ID must not be null");
        }

        if (equipmentIds.size() != startDateList.size() || startDateList.size() != endDateList.size()) {
            throw new IllegalArgumentException("Sizes of equipmentIds, startDateList, and endDateList must match");
        }
    }

    public Users getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));
    }

    public Demand createDemandForUser(Users user) {
        Demand demand = new Demand();
        demand.setUser(user);
        demand.setStatus(Status.Pending);
        return demandRepository.save(demand);
    }

    public void createDemandedEquipments(Demand demand, List<Long> equipmentIds, List<LocalDate> startDateList, List<LocalDate> endDateList) {
        for (int i = 0; i < equipmentIds.size(); i++) {
            Long equipmentId = equipmentIds.get(i);
            LocalDate startDate = startDateList.get(i);
            LocalDate endDate = endDateList.get(i);

            Equipment equipment = getEquipmentById(equipmentId);
            DemandedEquipment demandedEquipment = new DemandedEquipment();
            demandedEquipment.setDemand(demand);
            demandedEquipment.setEquipment(equipment);
            demandedEquipment.setStartDate(startDate);
            demandedEquipment.setEndDate(endDate);

            if (demandedEquipmentService.isThisDemandedEquipmentAllowed(demandedEquipment)) {
                throw new IllegalArgumentException("Equipment is not available for the specified period.");
            }else{
                demand.getDemandedEquipments().add(demandedEquipment);

                demandEquipmentRepository.save(demandedEquipment);
            }
        }


    }

    public Equipment getEquipmentById(Long equipmentId) {
        return equipmentRepository.findById(equipmentId)
                .orElseThrow(() -> new EntityNotFoundException("Equipment not found"));
    }
}
