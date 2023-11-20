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

import java.util.List;
import java.util.Optional;


@Component
public class ReservationServiceImp implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EquipmentRepository equipmentRepository;

    public ReservationServiceImp(ReservationRepository reservationRepository, UserRepository userRepository, EquipmentRepository equipmentRepository) {
        this.reservationRepository = reservationRepository;
        this.equipmentRepository = equipmentRepository;
        this.userRepository = userRepository;
    }

    public ReservationServiceImp() {
    }

    @Override
    public void save(Reservation reservation, Long equi, Long userId) {
        // Validate reservation date is not in the past
        if (reservation.getStartDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Reservation date cannot be in the past");
        }

        // Validate start time is before end time
        if (reservation.getStartDate().isAfter(reservation.getEndDate())) {
            throw new IllegalArgumentException("Start time must be before end time");
        }

        Optional<Equipment> equipment = equipmentRepository.findById(equi);
        Optional<Users> user = userRepository.findById(userId);

        // Validate equipment availability
        validateEquipmentAvailability(equipment.get(), reservation.getStartDate(), reservation.getEndDate());

        // Validate no overlapping reservations
        validateNoOverlappingReservations(equipment.get(), reservation.getStartDate(), reservation.getEndDate());

        long daysBetween = ChronoUnit.DAYS.between(reservation.getStartDate(), reservation.getEndDate());
        double totalPrice = equipment.get().getPrice() * daysBetween;

        reservation.setUser(user.get());
        reservation.setEquipment(equipment.get());
        reservation.setTotalPrice(totalPrice);

        reservationRepository.save(reservation);
    }

    private void validateNoOverlappingReservations(Equipment equipment, LocalDate startDate, LocalDate endDate) {
        List<Reservation> overlappingReservations = reservationRepository.findOverlappingReservations(
                equipment, startDate, endDate);

        if (!overlappingReservations.isEmpty()) {
            throw new IllegalArgumentException("Equipment has overlapping reservations for the specified date range");
        }
    }

    private void validateEquipmentAvailability(Equipment equipment, LocalDate startDate, LocalDate endDate) {
        List<Reservation> conflictingReservations = reservationRepository.findConflictingReservations(
                equipment, startDate, endDate);

        if (!conflictingReservations.isEmpty()) {
            throw new IllegalArgumentException("Equipment is not available for the specified date range");
        }
    }

}
