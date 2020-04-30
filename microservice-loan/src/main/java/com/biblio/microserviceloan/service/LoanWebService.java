package com.biblio.microserviceloan.service;

import com.biblio.microserviceloan.model.Loan;

import java.util.List;

public interface LoanWebService {

    Loan getOne(Long id);

    List<Loan> findByUserIdAndReturnedIsFalseOrderByDateCreationAsc(Long userId);

    boolean modifyDateExpiration(Loan loan);


}
