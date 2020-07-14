package com.biblio.microservicereservation.web.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ReservationExistException extends RuntimeException {
    public ReservationExistException(String reservation_exist) {
        super(reservation_exist);
    }
}
