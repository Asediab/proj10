package com.biblio.microservicebatch.mail;

import com.biblio.microservicebatch.model.Loan;

import java.util.function.Consumer;

public interface MailSender extends Consumer<Loan> {
}
