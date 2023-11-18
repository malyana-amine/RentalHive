package com.example.RentalHive.service.imp;

import com.example.RentalHive.Entities.Reservation;
import com.example.RentalHive.repository.ReservationRepository;
import com.example.RentalHive.service.ReservationService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@RequiredArgsConstructor
public class ReservationServiceImp implements ReservationService {
    private final ReservationRepository reservationRepository;
    @Override
    public Reservation save(Reservation reservation){
        return reservationRepository.save(reservation);
    }
    @Override
    public List<Reservation> getAllReservation(){
        return reservationRepository.findAll();
    }
    public void reservation(Reservation reservation) {
        save(reservation);
    }

    public void checkNullInputs(Reservation reservation) {
    }
}
