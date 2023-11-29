package com.example.RentalHive.service.imp;

import com.example.RentalHive.entity.Contract;
import com.example.RentalHive.entity.Devis;
import com.example.RentalHive.entity.Status;
import com.example.RentalHive.repository.ContractRepsitory;
import com.example.RentalHive.repository.DevisRepository;
import com.example.RentalHive.service.imp.ContractServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.io.IOException;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ContractServiceImpTest {

    @Mock
    private ContractRepsitory contractRepository;

    @Mock
    private DevisRepository devisRepository;

    @InjectMocks
    private ContractServiceImp contractService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetContractById() throws ChangeSetPersister.NotFoundException {
        // Your test logic for getContractById method
    }


    @Test
    void testSaveContractWithInvalidDevisStatus() {
        // Create a test Devis with an invalid status
        Devis testDevis = new Devis();
        testDevis.setId(1L);
        testDevis.setStatus(Status.Pending);

        // Mock the behavior of the repository
        when(devisRepository.findById(1L)).thenReturn(Optional.of(testDevis));

        // Test the saveContract method with an invalid Devis status
        assertThrows(IllegalStateException.class, () -> {
            contractService.saveContract(new Contract(), 1L);
        });

        // Verify that the method findById was called with the correct parameter
        verify(devisRepository, times(1)).findById(1L);
    }

    @Test
    void testSaveContractWithNonexistentDevis() {
        // Mock the behavior of the repository to return an empty Optional
        when(devisRepository.findById(1L)).thenReturn(Optional.empty());

        // Test the saveContract method with a nonexistent Devis
        assertThrows(NoSuchElementException.class, () -> {
            contractService.saveContract(new Contract(), 1L);
        });

        // Verify that the method findById was called with the correct parameter
        verify(devisRepository, times(1)).findById(1L);
    }
}
