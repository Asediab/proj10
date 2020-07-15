package com.biblio.microservicereservation.web.controller;


import com.biblio.microservicereservation.model.Reservation;
import com.biblio.microservicereservation.service.ReservationService;
import com.biblio.microservicereservation.web.exeptions.ReservationExistException;
import com.biblio.microservicereservation.web.exeptions.ReservationsNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping(value = "/reservation/add")
    public ResponseEntity<Void> addReservation(@RequestBody @Valid Reservation reservation) {

        if (reservationService.isReservationPossible(reservation.getDocumentId())){
            Reservation newReservation = reservationService.saveNew(reservation);
            if (newReservation == null) {
                throw new ReservationExistException("Reservation exist");
            }
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            throw new ReservationExistException("Reservation not possible, limit exceeded");
        }
    }

    @DeleteMapping(value = "/reservation/delete")
    public ResponseEntity<Void> deleteReservation(@RequestBody Reservation reservation) {
        if (!reservationService.reservationExist(reservation)) {
            throw new ReservationExistException("Reservation not exist");
        }
        reservationService.delete(reservation);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping(value = "/reservation/byUser/{userId}")
    public List<Reservation> getByUserId (@PathVariable("userId") Long userId) throws ReservationsNotFoundException {
        List<Reservation> reservationList = reservationService.getReservationsByUserId(userId);
        if (reservationList.isEmpty()) {
            throw new ReservationsNotFoundException("Invalid userID or no reservations for this user");
        }
        return reservationList;
    }
}
