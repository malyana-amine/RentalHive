package com.example.RentalHive.web.rest;

import com.example.RentalHive.DTO.ContractDTO;
import com.example.RentalHive.service.ContractService;
import com.example.RentalHive.service.EntityDTOConverterService;
import com.example.RentalHive.entity.Contract;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Paths;

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
    public ResponseEntity<?> getContract(@PathVariable Long id) throws ChangeSetPersister.NotFoundException, MalformedURLException {
        Contract contract = contractService.getContractById(id);
        ContractDTO contractDTO = converterService.convertToDTO(contract);
//        return ResponseEntity.ok(contractDTO);

        Resource resource = new UrlResource(Paths.get(contractDTO.getFile()).toUri());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }

    @PostMapping("/add/{id}")
    public ResponseEntity<ContractDTO> saveContract(@RequestBody Contract contract1, @PathVariable Long id, HttpServletResponse response) throws ChangeSetPersister.NotFoundException, IOException {
        // Assuming contractService.saveContract returns the saved contract
        Contract savedContract = contractService.saveContract(contract1, id);

        // Convert the saved contract to DTO
        ContractDTO contractDTO = converterService.convertToDTO(savedContract);

        // Return the ResponseEntity with the DTO and HTTP status OK
        return ResponseEntity.ok(contractDTO);
    }
}
