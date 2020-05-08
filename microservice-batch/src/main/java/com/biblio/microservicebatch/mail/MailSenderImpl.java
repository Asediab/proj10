package com.biblio.microservicebatch.mail;

import com.biblio.microservicebatch.model.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MailSenderImpl implements MailSender {

    @Value("${spring.mail.username}")
    String emailFrom;

    @Autowired
    private JavaMailSender mailSender;


    @Override
    public void accept(Loan loan) {
        SimpleMailMessage email = constructEmail(loan);
        mailSender.send(email);
    }

    private SimpleMailMessage constructEmail(Loan loan) {

        String recipientAddress = loan.getUser().getEmail();
        String subject = "Rappel de retour de prêt";

        StringBuilder message = new StringBuilder("Cher lecteur,\r\n La date de retour d'ouvrage emprunté a été dépassée.\r\n");
        message.append("Ouvrage concerné :\r\n");
        message.append("\t").append(loan.getCopyOfDocument().getDocument().getTitre()).append(", date de retour prévue: ").append(loan.getDateExpiration());


        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message.toString());
        email.setFrom(emailFrom);
        return email;
    }

}
