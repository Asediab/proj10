package com.biblio.microservicemail.mail;

import com.biblio.microservicemail.DTO.ReservationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class MailSenderImpl  implements Consumer<ReservationDTO> {

    @Value("${spring.mail.username}")
    String emailFrom;

    @Autowired
    private JavaMailSender mailSender;


    @Override
    public void accept(ReservationDTO reservationDTO) {
        SimpleMailMessage email = constructEmail(reservationDTO);
        mailSender.send(email);
    }

    private SimpleMailMessage constructEmail(ReservationDTO reservationDTO) {

        String recipientAddress = reservationDTO.getUserEmail();
        String subject = "Reservation";

        StringBuilder message = new StringBuilder("Cher lecteur,\r\n Le document que vous avez réservé est maintenant disponible. Vous avez 48 heures pour le récupérer, après cette heure la réservation sera annulée.\r\n");
        message.append("Ouvrage concerné :\r\n");
        message.append("\t").append(reservationDTO.getDocumentName());


        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message.toString());
        email.setFrom(emailFrom);
        return email;
    }

}
