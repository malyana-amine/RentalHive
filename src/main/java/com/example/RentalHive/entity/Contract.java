package com.example.RentalHive.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Contract {
    @Id
    private Long id;
    private String signature;
    private String description;
    private String file;

    @OneToOne
    @MapsId
    @JoinColumn(name = "devis_id")
    private Devis devis;
}
