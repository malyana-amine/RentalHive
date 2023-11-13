package com.example.RentalHive.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate start_date;
    private LocalDate end_date;
    private Double total_price;

    @ManyToOne
    @JoinColumn(name = "user")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "equipment")
    private Equipment equipment;

}
