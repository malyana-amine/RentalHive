package com.example.RentalHive.service.imp;

import com.example.RentalHive.DTO.DemandDTO;
import com.example.RentalHive.entity.Demand;
import com.example.RentalHive.entity.Users;
import com.example.RentalHive.helpers.DemandValidation;
import com.example.RentalHive.repository.DemandeRepository;
import com.example.RentalHive.service.DemandeService;
import com.example.RentalHive.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class DemandeServiceImp implements DemandeService {
    private final DemandeRepository repository;
    private final ModelMapper mapper;
    private final UserService userService;
    private final DemandValidation demandValidation;

    @Autowired
    public DemandeServiceImp(DemandeRepository repository, ModelMapper mapper, UserService userService, DemandValidation demandValidation) {
        this.repository = repository;
        this.mapper = mapper;
        this.userService = userService;
        this.demandValidation = demandValidation;
    }

    @Override
    public Demand CreateDemand(Long userId, List<Long> equipmentIds, List<LocalDate> startDateList, List<LocalDate> endDateList) {

        demandValidation.validateInputs(userId, equipmentIds, startDateList, endDateList);
        Users user = demandValidation.getUserById(userId);
        Demand demand = demandValidation.createDemandForUser(user);
        demandValidation.createDemandedEquipments(demand, equipmentIds, startDateList, endDateList);
        return demand;

    }

    @Override
    public List<DemandDTO> findAll() {
        return repository
                .findAll()
                .stream()
                .map(demand -> mapper.map(demand, DemandDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<DemandDTO> findById(Long aLong) {
        return repository
                .findById(aLong)
                .map(demand -> mapper.map(demand, DemandDTO.class));
    }

    @Override
    public DemandDTO update(DemandDTO entityDTO) {
        Demand demand = mapper.map(entityDTO, Demand.class);

        userService
                .findById(demand.getUser().getId())
                .orElseThrow(() -> new RuntimeException("user not found"));

        Demand saved = repository.save(demand);
        return mapper.map(saved, DemandDTO.class);
    }
}
