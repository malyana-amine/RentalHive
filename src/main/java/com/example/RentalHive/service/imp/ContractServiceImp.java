package com.example.RentalHive.service.imp;

import com.example.RentalHive.Util.PDFGenerator;
import com.example.RentalHive.entity.Devis;
import com.example.RentalHive.entity.Status;
import com.example.RentalHive.repository.DevisRepository;
import com.example.RentalHive.service.ContractService;
import com.example.RentalHive.entity.Contract;
import com.example.RentalHive.repository.ContractRepsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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

        return  contractRepository.findContractWithDetails(id);

    }

    @Override
    public Contract saveContract(Contract contract, Long id) throws IOException {
        Optional<Devis> devisOptional = devisRepository.findById(id);

        if (devisOptional.isPresent()) {
            Devis devis = devisOptional.get();

            // Check if the Devis status is Approved
            if (devis.getStatus() == Status.Approved) {
                contract.setDevis(devis);

                // Set up the file path to save the PDF
                DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD_HH-MM-SS");
                String currentDateTime = dateFormat.format(new Date());
                String filePath = "PDFs/" + currentDateTime + ".pdf";
//        String filePath = "com/example/RentalHive/Util/" + currentDateTime + ".pdf";
                File file = new File(filePath);
                file.getParentFile().mkdirs();

                PDFGenerator generator = new PDFGenerator();

                generator.generate(contract, filePath);

                contract.setFile(filePath);


                return contractRepository.save(contract);
            } else {
                throw new IllegalStateException("Cannot save contract for Devis with status: " + devis.getStatus());
            }
        } else {
            throw new NoSuchElementException("Devis with ID " + id + " not found");
        }
    }

}
