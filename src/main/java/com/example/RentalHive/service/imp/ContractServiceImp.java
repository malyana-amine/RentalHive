package com.example.RentalHive.service.imp;

import com.example.RentalHive.entity.Devis;
import com.example.RentalHive.repository.DevisRepository;
import com.example.RentalHive.service.ContractService;
import com.example.RentalHive.entity.Contract;
import com.example.RentalHive.repository.ContractRepsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Component;

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
    public Contract saveContract(Contract contract , Long id) {

        Optional<Devis> devis = devisRepository.findById(id);

        contract.setDevis(devis.get());
        return contractRepository.save(contract);
    }
}
