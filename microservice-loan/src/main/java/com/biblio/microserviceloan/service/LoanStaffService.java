package com.biblio.microserviceloan.service;

import com.biblio.microserviceloan.model.Loan;

import java.util.List;

public interface LoanStaffService {

    Loan getOne(Long id);

    void delete(Loan loan);

    Loan saveNew(Loan newLoan);

    Loan returnLoan(Loan loan);

    boolean existByModel(Loan loan);

    List<Loan> listLoanByDate();

}
