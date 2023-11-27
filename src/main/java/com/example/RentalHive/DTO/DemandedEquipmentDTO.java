package com.example.RentalHive.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DemandedEquipmentDTO {
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private DemandDTO demand;
    private EquipmentDTO equipment;
}
