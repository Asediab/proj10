package com.biblio.microservicemail.service.impl;

import com.biblio.microservicemail.DTO.ReservationDTO;
import com.biblio.microservicemail.mail.MailSenderImpl;
import com.biblio.microservicemail.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private MailSenderImpl mailSender;

    @Override
    public boolean sendEmail(ReservationDTO reservationDTO) {
        try {
            mailSender.accept(reservationDTO);
            return true;
        }catch (Exception exception){
            exception.printStackTrace();
            return false;
        }
    }
}
