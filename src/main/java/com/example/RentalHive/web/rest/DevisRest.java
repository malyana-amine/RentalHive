package com.example.RentalHive.web.rest;

import com.example.RentalHive.entity.Devis;
import com.example.RentalHive.service.DevisService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping ("/api/devis")
public class DevisRest {
    private final DevisService devisService;
    Logger logger = LoggerFactory.getLogger(DevisRest.class);
    @PostMapping("/{id}/generate")
    public ResponseEntity<ApiResponse<Devis>> generateDevis(@PathVariable Long id){
        try {

            Devis savedDevis = devisService.generateDevis(id);

            return ResponseEntity
                    .ok()
                    .body(ApiResponse.success( "devis generated successful", savedDevis));


        } catch (IllegalArgumentException e) {

            logger.error(e.getMessage(), e);

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.error(e.getMessage()));

        } catch (Exception e) {

            logger.error(e.getMessage(), e);


            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error(e.getMessage()));
        }
    }

}
