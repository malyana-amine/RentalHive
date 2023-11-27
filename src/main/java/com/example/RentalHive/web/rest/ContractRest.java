package com.example.RentalHive.web.rest;

import com.example.RentalHive.DTO.ContractDTO;
import com.example.RentalHive.service.ContractService;
import com.example.RentalHive.service.EntityDTOConverterService;
import com.example.RentalHive.entity.Contract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contracts")
public class ContractRest {
    @Autowired
    private final ContractService contractService;

    @Autowired
    @Qualifier("entityDTOConverterService")
    private final EntityDTOConverterService converterService;


    public ContractRest(ContractService contractService, @Qualifier("entityDTOConverterService") EntityDTOConverterService converterService) {
        this.contractService = contractService;
        this.converterService = converterService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContractDTO> getContract(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        Contract contract = contractService.getContractById(id);
        ContractDTO contractDTO = converterService.convertToDTO(contract);
        return ResponseEntity.ok(contractDTO);
    }
    @PostMapping ("/add")
    public ResponseEntity<ContractDTO> saveContract(@RequestBody Contract contract1) throws ChangeSetPersister.NotFoundException {
        Contract contract = contractService.saveContract(contract1);
        ContractDTO contractDTO = converterService.convertToDTO(contract);
        return ResponseEntity.ok(contractDTO);
    }
}
