package com.example.RentalHive.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;

    @ManyToOne
    private Role role;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private List<Demand> demands;
}
