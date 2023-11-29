package com.example.RentalHive.service.imp;

import com.example.RentalHive.entity.Demand;
import com.example.RentalHive.entity.Devis;
import com.example.RentalHive.entity.Status;
import com.example.RentalHive.repository.DevisRepository;
import com.example.RentalHive.service.DemandeService;
import com.example.RentalHive.service.DevisService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class DevisServiceImp implements DevisService {

    private final DevisRepository devisRepository;
    private final DemandeService demandeService;

    @Override
    public Devis generateDevis(Long id) {

        Demand demand = demandeService.findById(id).orElseThrow();
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

        return devisRepository.save(devis);
    }

        //    private final DevisRepository devisRepository   ;
//
//    public void verifierEtMettreAJourStatut() {
//        Date currentDate = new Date();
//        List<Devis> devisEnAttente = devisRepository.findExpiredPendingDevis(currentDate);
//
//        devisEnAttente.forEach(devis -> {
//            devis.setStatus(Status.Rejected);
//            devisRepository.save(devis); // Save the updated status to the database
//        });
//    }

}
