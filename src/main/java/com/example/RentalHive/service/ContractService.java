package com.example.RentalHive.service;

import com.example.RentalHive.entity.Contract;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

@Service
public interface ContractService {
    public Contract getContractById(Long id) throws ChangeSetPersister.NotFoundException;
    public Contract saveContract(Contract contract, Long id);
}
