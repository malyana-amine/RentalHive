package com.example.RentalHive.Service.imp;

import com.example.RentalHive.Service.ContractService;
import com.example.RentalHive.entity.Contract;
import com.example.RentalHive.repository.ContractRepsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Component;

@Component
public class ContractServiceImp implements ContractService {

    @Autowired
    private ContractRepsitory contractRepository;


    public ContractServiceImp(ContractRepsitory contractRepository) {
        this.contractRepository = contractRepository;
    }

    public Contract getContractById(Long id) throws ChangeSetPersister.NotFoundException {
        return contractRepository.findById(id)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());
    }

    @Override
    public Contract saveContract(Contract contract) {
        return contractRepository.save(contract);
    }
}
