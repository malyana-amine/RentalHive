package com.example.RentalHive.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Demand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Status status;

    @ManyToOne
    private Users user;

    @OneToMany(mappedBy = "demand")
    @ToString.Exclude
    @JsonIgnore
    private List<DemandedEquipment> demandedEquipments  = new ArrayList<>();;

    @OneToMany(mappedBy = "demand")
    @ToString.Exclude
    @JsonIgnore
    private List<Devis> devisList  = new ArrayList<>();;

}
