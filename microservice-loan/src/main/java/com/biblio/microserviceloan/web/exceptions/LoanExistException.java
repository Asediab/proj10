package com.biblio.microserviceloan.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class LoanExistException extends RuntimeException {
    public LoanExistException(String loan_exist) {
        super(loan_exist);
    }
}
