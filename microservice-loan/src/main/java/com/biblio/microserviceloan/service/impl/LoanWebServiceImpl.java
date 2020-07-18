package com.biblio.microserviceloan.service.impl;

import com.biblio.microserviceloan.dao.LoanDAO;
import com.biblio.microserviceloan.model.Loan;
import com.biblio.microserviceloan.service.LoanWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LoanWebServiceImpl implements LoanWebService {

    @Autowired
    private LoanDAO loanDAO;

    @Override
    public Loan getOne(Long id) {
        return loanDAO.getOne(id);
    }

    @Override
    public List<Loan> findByUserIdAndReturnedIsFalseOrderByDateCreationAsc(Long userId) {
        return loanDAO.findByUserIdAndReturnedIsFalseOrderByDateCreationAsc(userId);
    }


    @Override
    public boolean modifyDateExpiration(Loan loan) {
        if (loan.getNumberOfRenewals() == 0 && !loan.getDateExpiration().isBefore(LocalDate.now())) {
            loan.setDateExpiration(loan.getDateExpiration().plusMonths(1));
            loan.setNumberOfRenewals(1);
            loanDAO.save(loan);
            return true;
        }
        return false;
    }
}
