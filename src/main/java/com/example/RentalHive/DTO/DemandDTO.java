package com.example.RentalHive.DTO;

import com.example.RentalHive.entity.Status;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DemandDTO {
    private Long id;

    @NotNull(message = "Status cannot be null")
    private Status status;

    @NotNull(message = "User cannot be null")
    private UsersDTO user;

    private List<DemandedEquipmentDTO> demandedEquipments;
    private List<DevisDTO> devisList;
}