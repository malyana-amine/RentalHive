package com.example.RentalHive.web.rest;

import com.example.RentalHive.Entities.*;
import com.example.RentalHive.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/reservation")
public class ReservationRest {
    private final ReservationService reservationService;
    @PostMapping("/save")
    public Reservation saveReservation(@RequestBody Reservation reservation){
        Type type = Type.builder()
                .name("trax")
                .build();
        Equipment equipment = Equipment.builder()
                .name("camion")
                .price(100.0)
                .status(EquipmentStatus.AVAILABLE)
                .type(type)
                .build();
        Role role = Role.builder()
                .name("ROLE_ADMIN")
                .build();
        Users user = Users.builder()
                .username("mouad")
                .email("mouad@gmail.com")
                .password("test@@")
                .role(role)
                .build();
        Reservation reservation1 = Reservation.builder()
                .startDate( LocalDate.now())
                .endDate(LocalDate.now().plusWeeks(1))
                .totalPrice(700.0)
                .user(user)
                .equipment(equipment)
                .build();

        return reservationService.save(reservation1);
    }

    @GetMapping("/")
    public List<Reservation> getAllReservation(){
        return reservationService.getAllReservation();
    }
}

