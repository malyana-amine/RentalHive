package com.example.RentalHive.web.rest;

import com.example.RentalHive.Entities.*;
import com.example.RentalHive.repository.EquipmentRepository;
import com.example.RentalHive.repository.UserRepository;
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
    private final UserRepository userRepository;
    private final EquipmentRepository equipmentRepository;

    @PostMapping("/save")
    public Reservation saveReservation(@RequestBody Reservation reservation){
        reservation.setUser(
                userRepository.findById(reservation.getUser().getId()).orElseThrow()
        );
        reservation.setEquipment(
            equipmentRepository.findById(reservation.getEquipment().getId()).orElseThrow()
        );

        return reservationService.save(reservation);
    }

    @GetMapping("/getAll")
    public List<Reservation> getAllReservation(){
        return reservationService.getAllReservation();
    }
}

