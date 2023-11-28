package com.example.RentalHive.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Devis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Status status;
    private Double priceTotal;
    private Date dateCreation;
    private Date dateExpiration;

    @ManyToOne
    private Demand demand;

    @OneToOne(mappedBy = "devis")
    @PrimaryKeyJoinColumn
    private Contract contract;
}
