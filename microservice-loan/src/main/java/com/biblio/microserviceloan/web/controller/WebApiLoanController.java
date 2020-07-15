package com.biblio.microserviceloan.web.controller;

import com.biblio.microserviceloan.model.Loan;
import com.biblio.microserviceloan.service.LoanWebService;
import com.biblio.microserviceloan.web.exceptions.LoansNotFoundException;
import com.biblio.microserviceloan.web.exceptions.ProlongateLoanPeriodException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WebApiLoanController {

    @Autowired
    private LoanWebService loanWebService;

//    @PreAuthorize("#hasRole('USER')")
    @GetMapping(value = "/loans/{userId}")
    public List<Loan> listLoansByUser(@PathVariable("userId") Long userId) throws LoansNotFoundException {
        List<Loan> loanList = loanWebService.findByUserIdAndReturnedIsFalseOrderByDateCreationAsc(userId);
        if (loanList.isEmpty()) {
            throw new LoansNotFoundException("Invalid userID or no loans for this user");
        }
        return loanList;
    }


    //    @PreAuthorize("#hasRole('USER')")
    @PutMapping(value = "/loans/prolongateLoan/{loanID}")
    public ResponseEntity<Void> prolongateLoanPeriod(@PathVariable("loanID") Long loanID) {
        Loan loanProlongate = loanWebService.getOne(loanID);
        if (loanProlongate == null) {
            return ResponseEntity.noContent().build();
        }
        if (loanWebService.modifyDateExpiration(loanProlongate)) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            throw new ProlongateLoanPeriodException("Loan cannot be prolonged twice");
        }
    }

    @GetMapping(value = "/loans/documentId/{documentId}")
    public List<Loan> listLoansByDocumentId(@PathVariable("documentId") Long documentId) throws LoansNotFoundException {
        List<Loan> loanList = loanWebService.findByDocumentIdAndSortByDateExpirationAsc(documentId);
        if (loanList.isEmpty()) {
            throw new LoansNotFoundException("Invalid documentId or no loans for this document");
        }
        return loanList;
    }
}
