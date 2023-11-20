package com.example.RentalHive.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @ManyToOne
    @JoinColumn(name = "type_id")
    @JsonBackReference
    @NotNull(message = "The type of the equipment can not be null")
    private Type type;


    private EquipmentStatus status;

    @Positive(message = "The rent can not be negative or zero")
    private Double price;

}
