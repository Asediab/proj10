package com.biblio.microservicebatch.service;


import com.biblio.microservicebatch.model.ReservationDTO;

public interface MailService {

    boolean sendEmail(ReservationDTO reservationDTO);
}
