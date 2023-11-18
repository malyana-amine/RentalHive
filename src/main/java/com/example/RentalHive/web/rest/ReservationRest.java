package com.example.RentalHive.web.rest;

import com.example.RentalHive.Entities.Reservation;
import com.example.RentalHive.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reserve")
public class ReservationRest {
    // start dade -- endddate -- total price = price of  equi * days -- id user --id equipment
    private ReservationService reservationService;
    public ReservationRest (ReservationService reservationService){
        this.reservationService = reservationService ;
    }


    @PostMapping("/reserver/{userid}/{equi}")

    public void addReservation(@RequestBody Reservation reservation, @PathVariable Long equi ,@PathVariable Long userid){
        reservationService.save(reservation,equi,userid);
    }

}
