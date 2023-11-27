package com.example.RentalHive.service.imp;

import com.example.RentalHive.entity.*;
import com.example.RentalHive.repository.DemandEquipmentRepository;
import com.example.RentalHive.repository.DemandeRepository;
import com.example.RentalHive.repository.EquipmentRepository;
import com.example.RentalHive.repository.UserRepository;
import com.example.RentalHive.service.CrudService;
import com.example.RentalHive.service.DemandeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;



@Component
@RequiredArgsConstructor
public class DemandeServiceImp implements DemandeService {

    private final DemandeRepository demandRepository;
    private final UserRepository userRepository;
    private final EquipmentRepository equipmentRepository;
    private final DemandEquipmentRepository demandEquipmentRepository;


    @Override
    public Demand CreateDemand(Long userId, List<Long> equipmentIds ,  List<LocalDate> startDateList , List<LocalDate> endDateList) {

        if (userId == null) {
            throw new IllegalArgumentException("User ID must not be null");
        }

        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));

        Demand demand = new Demand();
        demand.setUser(user);
        demand.setStatus(Status.Pending);

        demand = demandRepository.save(demand);

        if (equipmentIds.size() != startDateList.size() || startDateList.size() != endDateList.size()) {
            throw new IllegalArgumentException("Sizes of equipmentIds, startDateList, and endDateList must match");
        }

        for (int i = 0; i < equipmentIds.size(); i++) {
            Long equipmentId = equipmentIds.get(i);
            LocalDate startDate = startDateList.get(i);
            LocalDate endDate = endDateList.get(i);

            Equipment equipment = equipmentRepository.findById(equipmentId)
                    .orElseThrow(() -> new EntityNotFoundException("Equipment not found"));

            DemandedEquipment demandedEquipment = new DemandedEquipment();
            demandedEquipment.setDemand(demand);
            demandedEquipment.setEquipment(equipment);

            demandedEquipment.setStartDate(startDate);
            demandedEquipment.setEndDate(endDate);

            demandEquipmentRepository.save(demandedEquipment);
        }

        return demand;
    }

}
