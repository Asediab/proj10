package com.biblio.microserviceloan.web.controller;

import com.biblio.microserviceloan.model.Loan;
import com.biblio.microserviceloan.service.LoanStaffService;
import com.biblio.microserviceloan.web.exceptions.LoanExistException;
import com.biblio.microserviceloan.web.exceptions.LoansNotFoundException;
import com.biblio.microserviceloan.web.exceptions.WrongDateException;
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

    @PostMapping(value = "/staffApi/loans")
    public ResponseEntity<Void> addLoan(@RequestBody @Valid Loan loan) {
        Loan newLoan = loanStaffService.saveNew(loan);
        if (newLoan == null) {
            throw new LoanExistException("Loan exist");
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @PutMapping(value = "/staffApi/loans")
    public ResponseEntity<Void> returnLoan(@RequestBody @Valid Loan loan) {
        if (!loanStaffService.existByModel(loan)) {
            throw new LoanExistException("Loan not exist");
        }
        Loan returnLoan = loanStaffService.returnLoan(loan);

        return ResponseEntity.status(HttpStatus.OK).build();

    }

    @GetMapping(value = "/staffApi/loansByDate")
    public List<Loan> loansByDateOfExpiration(@RequestBody Loan loan) {
        List<Loan> loanList = loanStaffService.listLoanByDate(loan.getDateExpiration());
        if (loanList == null) {
            throw new WrongDateException("Invalid date received");
        }
        if (loanList.isEmpty()) {
            throw new LoansNotFoundException("No loans for a given date");
        }
        return loanList;
    }
}
