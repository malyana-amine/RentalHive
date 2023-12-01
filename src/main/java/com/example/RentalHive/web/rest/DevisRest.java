package com.example.RentalHive.web.rest;

import com.example.RentalHive.DTO.DevisDTO;
import com.example.RentalHive.entity.Devis;
import com.example.RentalHive.service.DevisService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping ("/api/devis")
public class DevisRest {
    private final DevisService devisService;
    Logger logger = LoggerFactory.getLogger(DevisRest.class);
    @PostMapping("/{id}/generate")
    public ResponseEntity<?> generateDevis(@PathVariable Long id){
        try {

            Devis savedDevis = devisService.generateDevis(id);

            return ResponseEntity
                    .ok()
                    .body(ApiResponse.success( "devis generated successfully", savedDevis));


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
    @GetMapping()
    public ResponseEntity<ApiResponse<List<DevisDTO>>> findAll(){
        List<DevisDTO> devis = devisService.findAll();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success("Devis retreived successfuly", devis));
    }
    @PostMapping("/update")
    public ResponseEntity<ApiResponse> verifyAndUpdateStatus(){
        devisService.verifyAndUpdateStatus();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success("devis status updated successfully", null));
    }

}
