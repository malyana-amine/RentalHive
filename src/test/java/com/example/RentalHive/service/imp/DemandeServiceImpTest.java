package com.example.RentalHive.service.imp;

import com.example.RentalHive.entity.Demand;
import com.example.RentalHive.entity.Users;
import com.example.RentalHive.repository.DemandeRepository;
import com.example.RentalHive.service.imp.DemandeServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import com.example.RentalHive.helpers.DemandValidation;


class DemandeServiceImpTest {

    @Mock
    private DemandValidation demandValidation;

    @Mock
    private DemandeRepository demandeRepository;

    @InjectMocks
    private DemandeServiceImp demandeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateDemand() {
        Long userId = 1L;
        List<Long> equipmentIds = Arrays.asList(1L, 2L);
        List<LocalDate> startDateList = Arrays.asList(LocalDate.now(), LocalDate.now().plusDays(1));
        List<LocalDate> endDateList = Arrays.asList(LocalDate.now().plusDays(5), LocalDate.now().plusDays(6));

        Users user = new Users();
        Demand demand = new Demand();

        when(demandValidation.getUserById(userId)).thenReturn(user);
        when(demandValidation.createDemandForUser(user)).thenReturn(demand);


        Demand result = demandeService.CreateDemand(userId, equipmentIds, startDateList, endDateList);

        assertEquals(demand, result);
        verify(demandValidation).validateInputs(userId, equipmentIds, startDateList, endDateList);
        verify(demandValidation).getUserById(userId);
        verify(demandValidation).createDemandForUser(user);
        verify(demandValidation).createDemandedEquipments(demand, equipmentIds, startDateList, endDateList);
    }

    @Test
    void testFindAll() {
        List<Demand> expectedDemands = Arrays.asList(new Demand(), new Demand());
        when(demandeRepository.findAll()).thenReturn(expectedDemands);

        List<Demand> result = demandeService.findAll();

        assertEquals(expectedDemands, result);
        verify(demandeRepository).findAll();
    }
}
