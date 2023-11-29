package com.example.RentalHive.DTO;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
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

    @FutureOrPresent(message = "Start date must be today or later")
    @NotNull(message = "Start date cannot be null")
    private LocalDate startDate;

    @Future(message = "End date must be greater than today")
    @NotNull(message = "End date cannot be null")
    private LocalDate endDate;

    @NotNull(message = "Equipment cannot be null")
    private EquipmentDTO equipment;
}
