package com.biblio.microserviceloan.web.exceptions;

public class WrongDateException extends RuntimeException {
    public WrongDateException(String invalid_date_received) {
        super(invalid_date_received);
    }
}
