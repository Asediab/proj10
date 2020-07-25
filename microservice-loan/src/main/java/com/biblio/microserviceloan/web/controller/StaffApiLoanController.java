package com.biblio.microserviceloan.web.controller;

import com.biblio.microserviceloan.model.Loan;
import com.biblio.microserviceloan.service.LoanStaffService;
import com.biblio.microserviceloan.web.exceptions.LoanExistException;
import com.biblio.microserviceloan.web.exceptions.LoansNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class StaffApiLoanController {

    @Autowired
    private LoanStaffService loanStaffService;

    @PostMapping(value = "/loans/staffApi")
    public ResponseEntity<Void> addLoan(@RequestBody @Valid Loan loan) {
        Loan newLoan = loanStaffService.saveNew(loan);
        if (newLoan == null) {
            throw new LoanExistException("Loan exist");
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping(value = "/loans/staffApi")
    public ResponseEntity<Void> returnLoan(@RequestBody @Valid Loan loan) {
        if (!loanStaffService.existByModel(loan)) {
            throw new LoanExistException("Loan not exist");
        }
        loanStaffService.returnLoan(loan);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping(value = "/loans/staffApi/loansByDate")
    public List<Loan> loansByDateOfExpiration() {
        List<Loan> loanList = loanStaffService.listLoanByDate();

        if (loanList.isEmpty()) {
            throw new LoansNotFoundException("No loans for a given date");
        }
        return loanList;
    }
}
