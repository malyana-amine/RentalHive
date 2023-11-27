package com.example.RentalHive.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EquipmentDTO {
    private Long id;
    private String name;
    private Double price;
    private String image;
    private TypeDTO type;
}