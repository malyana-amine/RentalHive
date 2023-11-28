package com.example.RentalHive.service.imp;

import com.example.RentalHive.entity.Devis;
import com.example.RentalHive.entity.Status;
import com.example.RentalHive.repository.DevisRepository;
import com.example.RentalHive.service.ContractService;
import com.example.RentalHive.entity.Contract;
import com.example.RentalHive.repository.ContractRepsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;
import java.util.Optional;

@Component
public class ContractServiceImp implements ContractService {

    @Autowired
    private ContractRepsitory contractRepository;
    @Autowired
    private final DevisRepository devisRepository;


    public ContractServiceImp(ContractRepsitory contractRepository, DevisRepository devisRepository) {
        this.contractRepository = contractRepository;
        this.devisRepository = devisRepository;
    }

    public Contract getContractById(Long id) throws ChangeSetPersister.NotFoundException {
        return contractRepository.findById(id)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());
    }

    @Override
    public Contract saveContract(Contract contract, Long id) {
        Optional<Devis> devisOptional = devisRepository.findById(id);

        if (devisOptional.isPresent()) {
            Devis devis = devisOptional.get();

            // Check if the Devis status is Approved
            if (devis.getStatus() == Status.Approved) {
                contract.setDevis(devis);
                return contractRepository.save(contract);
            } else {
                // Throw an exception or handle the case where the Devis status is not Approved
                throw new IllegalStateException("Cannot save contract for Devis with status: " + devis.getStatus());
            }
        } else {
            // Handle the case where the Devis with the specified ID is not found
            throw new NoSuchElementException("Devis with ID " + id + " not found");
        }
    }

}
