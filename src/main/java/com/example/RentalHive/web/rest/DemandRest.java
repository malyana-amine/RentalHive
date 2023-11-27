package com.example.RentalHive.web.rest;

import com.example.RentalHive.entity.Demand;
import com.example.RentalHive.entity.DemandedEquipment;
import com.example.RentalHive.service.DemandeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/demands")
public class DemandRest {

    private final DemandeService demandeService;

    @PostMapping("/add")
    public ResponseEntity<Demand> createDemand(
          @RequestParam(required=false) Long userId,
          @RequestParam(required=false) List<Long> equipmentIds,
          @RequestParam(required=false) List<LocalDate> startDateList,
          @RequestParam(required=false) List<LocalDate> endDateList) {

        Demand demand = demandeService.CreateDemand(userId,equipmentIds,startDateList,endDateList);

        return new ResponseEntity<>(demand, HttpStatus.CREATED);
    }

}
