package com.biblio.microservicemail.web.controller;

import com.biblio.microservicemail.DTO.ReservationDTO;
import com.biblio.microservicemail.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class MailController {

    @Autowired
    private MailService mailService;

    @PostMapping(value = "/mail/send")
    public ResponseEntity<Void> sendMAil(@RequestBody ReservationDTO reservationDTO) {

        if (!mailService.sendEmail(reservationDTO)) {
            throw new RuntimeException("Email not sent");
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
