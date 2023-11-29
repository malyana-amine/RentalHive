package com.example.RentalHive.web.rest;

import com.example.RentalHive.DTO.DemandDTO;
import com.example.RentalHive.entity.Demand;
import com.example.RentalHive.service.DemandeService;
import com.example.RentalHive.service.EntityDTOConverterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/demands")
public class DemandRest {

    private final DemandeService demandeService;

    @Autowired
    @Qualifier("entityDTOConverterService")
    private final EntityDTOConverterService converterService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<DemandDTO>> createDemand(
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) List<Long> equipmentIds,
            @RequestParam(required = false) List<LocalDate> startDateList,
            @RequestParam(required = false) List<LocalDate> endDateList) {

        try {
            Demand demand = demandeService.CreateDemand(userId, equipmentIds, startDateList, endDateList);
            DemandDTO demandDTO = converterService.convertToDTO(demand);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(ApiResponse.success("demand created successful", demandDTO));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.error(e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<DemandDTO>>> getAllDemands() {
        List<DemandDTO> demands = demandeService.findAll();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success("demands retrieved successful", demands));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DemandDTO>> getDemandById(@PathVariable Long id) {
        Optional<DemandDTO> demand = demandeService.findById(id);

        return demand
                .map(value -> ResponseEntity
                        .status(HttpStatus.OK)
                        .body(ApiResponse.success(null, value)))
                .orElseGet(() -> ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(ApiResponse.error("demand not found")));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<DemandDTO>> updateDemand(@PathVariable Long id, @RequestBody @Valid DemandDTO demandDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.validationError(bindingResult));
        }

        Optional<DemandDTO> existingDemand = demandeService.findById(id);

        if (existingDemand.isPresent()) {
            try {
                demandDTO.setId(id);
                DemandDTO updatedDemand = demandeService.update(demandDTO);
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(ApiResponse.success("demand updated successful", updatedDemand));
            } catch (Exception e) {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(ApiResponse.error(e.getMessage()));
            }
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error("demand not found"));
        }
    }
}
