package com.example.RentalHive.web.rest;

import com.example.RentalHive.entity.Demand;
import com.example.RentalHive.service.DemandeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/demands")
public class DemandRest {

    private final DemandeService demandeService;

    @PostMapping("/add")
    public ResponseEntity<Demand> createDemand(
          @RequestParam(required=false) Long userId,
            @RequestParam(required=false) List<Long> equipmentIds) {

        Demand demand = demandeService.CreateDemand(userId,equipmentIds);

        return new ResponseEntity<>(demand, HttpStatus.CREATED);
    }

}
