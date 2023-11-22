package com.example.RentalHive;

import com.example.RentalHive.Entity.Equipment;
import com.example.RentalHive.Entity.Users;
import com.example.RentalHive.Repository.EquipmentRepository;
import com.example.RentalHive.Repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


class ReservationServiceImpTest {

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private EquipmentRepository equipmentRepository;

    @InjectMocks
    private ReservationServiceImp reservationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSave() {
        // Arrange
        Reservation reservation = new Reservation();
        reservation.setStartDate(LocalDate.now());
        reservation.setEndDate(LocalDate.now().plusDays(2));

        Long equipmentId = 1L;
        Long userId = 2L;

        Equipment equipment = new Equipment();
        equipment.setPrice(10.0);

        Users user = new Users();

        when(equipmentRepository.findById(equipmentId)).thenReturn(Optional.of(equipment));
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // Act
        reservationService.save(reservation, equipmentId, userId);

        // Assert
        verify(equipmentRepository, times(1)).findById(equipmentId);
        verify(userRepository, times(1)).findById(userId);
        verify(reservationRepository, times(1)).save(reservation);

        // Add more assertions based on your business logic and requirements
    }
    @Test
    public void testSaveReservationInPast() {
        Reservation reservation = new Reservation();
        reservation.setStartDate(LocalDate.now().minusDays(1));
        reservation.setEndDate(LocalDate.now().minusDays(3));

        assertThrows(IllegalArgumentException.class, () ->
                reservationService.save(reservation, 1L, 1L));
    }

    @Test
    public void testSaveInvalidDateRange() {
        Reservation reservation = new Reservation();
        reservation.setStartDate(LocalDate.now().plusDays(3));
        reservation.setEndDate(LocalDate.now().plusDays(1));

        assertThrows(IllegalArgumentException.class, () ->
                reservationService.save(reservation, 1L, 1L));
    }

}

