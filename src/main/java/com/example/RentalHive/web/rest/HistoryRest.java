package com.example.RentalHive.web.rest;

import com.example.RentalHive.Entities.Reservation;
import com.example.RentalHive.repository.HistoryReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/history")
public class HistoryRest {

    private final HistoryReservationRepository historyReservationRepository;
    @GetMapping("/equipmentName/{equipmentName}")
    public List<Reservation> getReservationByEquipmentName(@PathVariable String equipmentName){
        return historyReservationRepository.findReservationsByEquipmentName(equipmentName);
    }
}
