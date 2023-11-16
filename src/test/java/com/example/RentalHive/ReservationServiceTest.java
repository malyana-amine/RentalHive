package com.example.RentalHive;

import com.example.RentalHive.Entities.*;
import com.example.RentalHive.repository.ReservationRepository;
import com.example.RentalHive.service.imp.ReservationServiceImp;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ReservationServiceTest {
    private ReservationServiceImp reservationServiceImp;
    private ReservationRepository reservationRepository;

    @BeforeEach
    void beforeEach(){
        reservationServiceImp = new ReservationServiceImp();
    }
    @Test
    void whenNameIsNullThenThrowError() {
        Type type = Type.builder()
                .name("trax")
                .build();
        Equipment equipment = Equipment.builder()
                .name("camion")
                .price(100.0)
                .status(StatusEnum.dispo)
                .type(type)
                .build();
        Role role = Role.builder()
                .name("ROLE_ADMIN")
                .build();
        Users user = Users.builder()
                .userName("mouad")
                .email("mouad@gmail.com")
                .password("test@@")
                .role(role)
                .build();
        Reservation reservation = Reservation.builder()
                .start_date(LocalDate.now())
                .end_date(LocalDate.now().plusWeeks(1))
                .total_price(700.0)
                .user(user)
                .equipment(equipment)
                .build();

        assertThrows(IllegalArgumentException.class, () -> reservationServiceImp.reservation(reservation));
    }
    @Test
    void whenEndDateIsNullThenThrowError() {
        Reservation reservation = Reservation.builder()
                .end_date(null)
                .build();

        assertThrows(IllegalArgumentException.class, () -> reservationServiceImp.checkNullInputs(reservation));
    }
    @Test
    void whenStartDateIsNullThenThrowError() {
        Reservation reservation = Reservation.builder()
                .end_date(null)
                .build();

        assertThrows(IllegalArgumentException.class, () -> reservationServiceImp.checkNullInputs(reservation));
    }
    @Test
    void whenStartDateGraterThenEndDayThrowError() {
        Reservation reservation = Reservation.builder()
                .start_date(LocalDate.now())
                .end_date(LocalDate.now().minusDays(1))
                .build();

        assertThrows(IllegalArgumentException.class, () -> reservationServiceImp.checkNullInputs(reservation));
    }

    @AfterAll
    static void afterAllMsg(){
        System.out.println(" ------------------------------------------------------------------------------");
        System.out.println(" ----------- All the tests of authentication are passed successfully ----------");
        System.out.println(" ------------------------------------------------------------------------------");
    }

}
