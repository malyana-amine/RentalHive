package com.example.RentalHive.Service.imp;

import com.example.RentalHive.Entities.Equipment;
import com.example.RentalHive.Entities.Reservation;
import com.example.RentalHive.Entities.Users;
import com.example.RentalHive.repository.EquipmentRepository;
import com.example.RentalHive.repository.ReservationRepository;
import com.example.RentalHive.repository.UserRepository;
import com.example.RentalHive.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import java.util.Optional;


@Component
public class ReservationServiceImp implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EquipmentRepository equipmentRepository;


    public ReservationServiceImp(ReservationRepository reservationRepository , UserRepository userRepository,EquipmentRepository equipmentRepository){
        this.reservationRepository = reservationRepository;
        this.equipmentRepository = equipmentRepository;
        this.userRepository = userRepository;
    }
    public ReservationServiceImp(){
    }
    @Override
    public void save(Reservation reservation , Long equi , Long userid) {

        Optional<Equipment> equipment =  equipmentRepository.findById(equi);
        Optional<Users> user = userRepository.findById(userid);


        long daysBetween = ChronoUnit.DAYS.between(reservation.getStartDate(), reservation.getEndDate());

        double totalPrice = equipment.get().getPrice()*daysBetween;

        reservation.setUser(user.get());
        reservation.setEquipment(equipment.get());
        reservation.setTotalPrice(totalPrice);


        reservationRepository.save(reservation);
    }
//    public void reservation(Reservation reservation) {
//    }
//
//    public void checkNullInputs(Reservation reservation) {
//    }

}
