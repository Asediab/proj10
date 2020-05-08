package com.biblio.microserviceloan.dao;

import com.biblio.microserviceloan.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface LoanDAO extends JpaRepository<Loan, Long> {

    Loan getOne(Long id);

    List<Loan> findByUserIdAndReturnedIsFalseOrderByDateCreationAsc(Long userId);

    Loan findByUserIdAndCopyOfDocumentIdAndReturnedIsFalse(Long userId, Long documentId);

    List<Loan> findByDateExpirationLessThanAndReturnedIsFalse(LocalDate matchesDate);


}
