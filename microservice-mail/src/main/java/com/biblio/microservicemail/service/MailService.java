package com.biblio.microservicemail.service;

import com.biblio.microservicemail.DTO.ReservationDTO;

public interface MailService {

    boolean sendEmail(ReservationDTO reservationDTO);
}
