package com.biblio.microservicebatch.service.impl;

import com.biblio.microservicebatch.mail.MailSenderImplReservation;
import com.biblio.microservicebatch.model.ReservationDTO;
import com.biblio.microservicebatch.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private MailSenderImplReservation mailSender;

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
