package com.example.RentalHive.web.rest;

import com.example.RentalHive.Entities.Reservation;
import com.example.RentalHive.repository.HistoryReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/history")
public class HistoryRest {

    private final HistoryReservationRepository historyReservationRepository;
    @GetMapping("get")
    public List<Reservation> getReservationByEquipmentName(@RequestParam String equipmentName){
        return historyReservationRepository.findReservationsByEquipmentName(equipmentName);
    }
}
