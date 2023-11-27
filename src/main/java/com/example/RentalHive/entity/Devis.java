package com.example.RentalHive.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @JsonIgnore
    @PrimaryKeyJoinColumn
    private Contract contract;


}
