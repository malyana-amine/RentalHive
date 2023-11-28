package com.example.RentalHive.DTO;

import com.example.RentalHive.entity.DemandedEquipment;
import com.example.RentalHive.entity.Devis;
import com.example.RentalHive.entity.Status;
import com.example.RentalHive.entity.Users;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DemandDTO {

    @NotNull(message = "id could not be null")
    private Long id;

    @NotNull(message = "status could not be null")
    private Status status;

    @NotNull(message = "user id could not be null")
    private Long user_id;
}
