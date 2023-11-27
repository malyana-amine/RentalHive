package com.example.RentalHive.DTO;

import com.example.RentalHive.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DevisDTO {
    private Long id;
    private Status status;
    private Double priceTotal;
    private ContractDTO contract;
}
