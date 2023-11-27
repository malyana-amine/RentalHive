package com.example.RentalHive.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Equipment {

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double price;

    private String image;

    @ManyToOne
    @ToString.Exclude
    @JsonIgnore
    private Type type;

    @OneToMany(mappedBy = "equipment",fetch = FetchType.LAZY)
    @JsonIgnore
    @ToString.Exclude
    private List<DemandedEquipment> demandedEquipments;

}
