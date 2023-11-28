package com.example.RentalHive.web.rest;

import com.example.RentalHive.DTO.DemandDTO;
import com.example.RentalHive.DTO.DemandedEquipmentDTO;
import com.example.RentalHive.entity.Demand;
import com.example.RentalHive.entity.DemandedEquipment;
import com.example.RentalHive.service.ContractService;
import com.example.RentalHive.service.DemandeService;
import com.example.RentalHive.service.DemandedEquipmentService;
import com.example.RentalHive.service.EntityDTOConverterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/demands")
public class DemandRest {

    private final DemandeService demandeService;
    private final ModelMapper mapper;
    private final DemandedEquipmentService demandedEquipmentService;

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

    @PostMapping("/eq")
    public ResponseEntity<ApiResponse<DemandedEquipmentDTO>> createDemandedEquipment(@RequestBody @Valid DemandedEquipmentDTO demandedEquipmentDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.validationError(bindingResult));
        }

        try {
            DemandedEquipment demandedEquipment = mapper.map(demandedEquipmentDTO, DemandedEquipment.class);
            DemandedEquipment saved = demandedEquipmentService.save(demandedEquipment);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(ApiResponse.success("", mapper.map(saved, DemandedEquipmentDTO.class)));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.error(e.getMessage()));
        }
    }

}
