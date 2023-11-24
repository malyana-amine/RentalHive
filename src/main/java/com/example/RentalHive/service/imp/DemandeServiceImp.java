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
    public Demand CreateDemand(Long userId, List<Long> equipmentIds) {

        if (userId == null) {
            throw new IllegalArgumentException("User ID must not be null");
        }

        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));


        Demand demand = new Demand();
        demand.setUser(user);
        demand.setStatus(Status.Pending);


        demand = demandRepository.save(demand);


        for (Long equipmentId : equipmentIds) {
            Equipment equipment = equipmentRepository.findById(equipmentId)
                    .orElseThrow(() -> new EntityNotFoundException("Equipment not found"));

            DemandedEquipment demandedEquipment = new DemandedEquipment();
            demandedEquipment.setDemand(demand);
            demandedEquipment.setEquipment(equipment);


            demandedEquipment.setStartDate(LocalDate.now());
            demandedEquipment.setEndDate(LocalDate.now().plusDays(7)); // Adjust as needed


            demandEquipmentRepository.save(demandedEquipment);
        }


        return demand;
    }

}
