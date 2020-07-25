package com.biblio.microserviceloan.service.impl;

import com.biblio.microserviceloan.DTO.ReservationDTO;
import com.biblio.microserviceloan.dao.LoanDAO;
import com.biblio.microserviceloan.model.Loan;
import com.biblio.microserviceloan.proxy.MicroserviceDocumentProxy;
import com.biblio.microserviceloan.proxy.MicroserviceReservationProxy;
import com.biblio.microserviceloan.service.LoanStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LoanStaffServiceImpl implements LoanStaffService {

    @Autowired
    private LoanDAO loanDAO;

    @Autowired
    private MicroserviceDocumentProxy documentProxy;

    @Autowired
    private MicroserviceReservationProxy reservationProxy;

    @Override
    public Loan getOne(Long id) {
        return loanDAO.getOne(id);
    }

    @Override
    public void delete(Loan loan) {

        loanDAO.delete(loan);
    }

    @Override
    public Loan saveNew(Loan newLoan) {
        if (!loanExist(newLoan.getUserId(), newLoan.getCopyOfDocumentId())) {
            newLoan.setDateCreation(LocalDate.now());
            newLoan.setDateExpiration(newLoan.getDateCreation().plusMonths(1));
            newLoan.setNumberOfRenewals(0);
            newLoan.setReturned(Boolean.FALSE);
            documentProxy.deleteCopyAvailable(newLoan.getDocumentId());
            reservationProxy.deleteReservation(this.newReservation(newLoan));
            return loanDAO.save(newLoan);
        }
        return null;
    }

    private ReservationDTO newReservation(Loan loan){
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setDocumentId(loan.getDocumentId());
        reservationDTO.setUserId(loan.getUserId());

        return reservationDTO;

    }

    @Override
    public Loan returnLoan(Loan loan) {
        if (loanExist(loan.getUserId(), loan.getCopyOfDocumentId())) {
            documentProxy.addCopyAvailable(loan.getDocumentId());
            reservationProxy.sendMail(loan.getDocumentId());
            loan.setReturned(Boolean.TRUE);
            return loanDAO.save(loan);
        }
        return null;
    }

    private boolean loanExist(Long userId, Long documentID) {
        Loan loan = loanDAO.findByUserIdAndCopyOfDocumentIdAndReturnedIsFalse(userId, documentID);
        if (loan == null) {
            return false;
        }
        return true;
    }

    @Override
    public boolean existByModel(Loan loan) {
        Example<Loan> example = Example.of(loan);
        return loanDAO.exists(example);
    }

    @Override
    public List<Loan> listLoanByDate() {
        LocalDate date = LocalDate.now();

        return loanDAO.findByDateExpirationLessThanAndReturnedIsFalse(date);
    }
}
