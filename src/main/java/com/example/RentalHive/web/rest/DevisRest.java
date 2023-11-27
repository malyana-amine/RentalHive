package com.example.RentalHive.web.rest;

import com.example.RentalHive.entity.Devis;
import com.example.RentalHive.entity.Equipment;
import com.example.RentalHive.service.DevisService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping ("/api/devis")
public class DevisRest {
    private final DevisService devisService;
    @PostMapping("/save")
    public ResponseEntity<Devis> generateDevis(Devis devis){
        try {
            Devis savedDevis = devisService.generateDevis(devis);

            return new ResponseEntity<>(savedDevis, HttpStatus.CREATED);

        } catch (IllegalArgumentException e) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
