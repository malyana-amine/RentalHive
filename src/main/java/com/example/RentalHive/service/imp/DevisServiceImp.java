package com.example.RentalHive.service.imp;

import com.example.RentalHive.DTO.DemandDTO;
import com.example.RentalHive.DTO.DevisDTO;
import com.example.RentalHive.entity.Demand;
import com.example.RentalHive.entity.Devis;
import com.example.RentalHive.entity.Status;
import com.example.RentalHive.repository.DevisRepository;
import com.example.RentalHive.service.DemandeService;
import com.example.RentalHive.service.DevisService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DevisServiceImp implements DevisService {

    private final DevisRepository devisRepository;
    private final DemandeService demandeService;
    private final ModelMapper modelMapper;
   // private final Function<Devis, DevisDTO> mapDevisToDto = (element) -> modelMapper.map(element, DevisDTO.class);

    @Override
    public Devis generateDevis(Long id) {

        DemandDTO demand = demandeService.findById(id).orElseThrow();

        if (demand.getStatus() != Status.Approved) {
            throw new IllegalArgumentException("This demand is not approved !!");
        }
        /* ** Set the expiration date to 24 hours from the current date ** */
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.HOUR_OF_DAY, 24);


        Devis devis = new Devis();

        devis.setDateCreation(new Date());
        devis.setDateExpiration(calendar.getTime());

        Double sum = demand.getDemandedEquipments().stream()
                .map(demandedEquipment -> demandedEquipment.getEquipment().getPrice())
                .mapToDouble(Double::doubleValue).sum();

        devis.setPriceTotal(sum);
        devis.setStatus(Status.Pending);
        devis.setDemand(modelMapper.map(demand, Demand.class));

        return devisRepository.save(devis);
    }
    @Override
    public void verifyAndUpdateStatus() {
        Date currentDate = new Date();
        List<Devis> devisPending = devisRepository.findExpiredPendingDevis(currentDate);

        devisPending.stream()
                .forEach(devis -> {
                    devis.setStatus(Status.Rejected);
                    devisRepository.save(devis);
                });
    }
    @Override
    public List<DevisDTO> findAll(){
        return devisRepository
                .findAll()
                .stream()
                .map(devis -> {
                    return modelMapper.map( devis, DevisDTO.class);
                })
                .collect(Collectors.toList());
    }

}
