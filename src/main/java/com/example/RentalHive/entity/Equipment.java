package com.example.RentalHive.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "The name of equipment can not be empty")
    @NotNull(message = "The name of equipment can not be null")
    @NotBlank(message = "The name of equipment can not be blank")
    private String name;

    @Positive(message = "The rent can not be negative or zero")
    private Double price;

    private String image;

    @ManyToOne
    @NotNull(message = "The type of the equipment can not be null")
    private Type type;

    @OneToMany(mappedBy = "equipment")
    @ToString.Exclude
    private List<DemandedEquipment> demandedEquipments;
}
