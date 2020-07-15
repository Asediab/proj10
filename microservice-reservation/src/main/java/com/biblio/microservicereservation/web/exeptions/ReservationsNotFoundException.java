package com.biblio.microservicereservation.web.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ReservationsNotFoundException extends RuntimeException {
    public ReservationsNotFoundException(String s) {
        super(s);
    }
}
