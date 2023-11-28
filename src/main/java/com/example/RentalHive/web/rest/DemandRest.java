package com.example.RentalHive.web.rest;

import com.example.RentalHive.DTO.DemandDTO;
import com.example.RentalHive.entity.Demand;
import com.example.RentalHive.entity.DemandedEquipment;
import com.example.RentalHive.service.ContractService;
import com.example.RentalHive.service.DemandeService;
import com.example.RentalHive.service.EntityDTOConverterService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    @Autowired
    @Qualifier("entityDTOConverterService")
    private final EntityDTOConverterService converterService;
    @PostMapping("/add")
    public ResponseEntity<DemandDTO> createDemand(
          @RequestParam(required=false) Long userId,
          @RequestParam(required=false) List<Long> equipmentIds,
          @RequestParam(required=false) List<LocalDate> startDateList,
          @RequestParam(required=false) List<LocalDate> endDateList) {

        Demand demand = demandeService.CreateDemand(userId,equipmentIds,startDateList,endDateList);
        DemandDTO demandDTO = converterService.convertToDTO(demand);
        return  ResponseEntity.ok(demandDTO);
    }

    @GetMapping("/")
    public ResponseEntity<List<DemandDTO>> getDemandDTO(){
        List<Demand> demandList = demandeService.findAll();
        List<DemandDTO> demandDTOList =converterService.convertToDTOList(demandList);



        return ResponseEntity.ok(demandDTOList);
    }

}
