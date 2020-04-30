package com.biblio.microserviceloan.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class ProlongateLoanPeriodException extends RuntimeException {
    public ProlongateLoanPeriodException(String s) {
        super(s);
    }
}
