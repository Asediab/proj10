package com.biblio.microserviceloan.service.impl;

import com.biblio.microserviceloan.dao.LoanDAO;
import com.biblio.microserviceloan.model.Loan;
import com.biblio.microserviceloan.proxy.MicroserviceDocumentProxy;
import com.biblio.microserviceloan.proxy.MicroserviceReservationProxy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class LoanStaffServiceImplTest {
    @Mock
    LoanDAO loanDAO;
    @Mock
    MicroserviceDocumentProxy documentProxy;
    @Mock
    MicroserviceReservationProxy reservationProxy;
    @InjectMocks
    LoanStaffServiceImpl loanStaffServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetOne() {
        when(loanDAO.getOne(anyLong())).thenReturn(new Loan());

        Loan result = loanStaffServiceImpl.getOne(Long.valueOf(1));
        Assertions.assertEquals(new Loan(), result);
    }

    @Test
    void testDelete() {
        loanStaffServiceImpl.delete(new Loan());
    }

    @Test
    void testSaveNew() {
        when(loanDAO.findByUserIdAndCopyOfDocumentIdAndReturnedIsFalse(anyLong(), anyLong())).thenReturn(new Loan());
        when(documentProxy.deleteCopyAvailable(anyLong())).thenReturn(null);
        when(reservationProxy.deleteReservation(any())).thenReturn(null);

        Loan result = loanStaffServiceImpl.saveNew(new Loan());
        Assertions.assertEquals(new Loan(), result);
    }

    @Test
    void testReturnLoan() {
        when(loanDAO.findByUserIdAndCopyOfDocumentIdAndReturnedIsFalse(anyLong(), anyLong())).thenReturn(new Loan());
        when(documentProxy.addCopyAvailable(anyLong())).thenReturn(null);
        when(reservationProxy.sendMail(anyLong())).thenReturn(null);

        Loan result = loanStaffServiceImpl.returnLoan(new Loan());
        Assertions.assertEquals(new Loan(), result);
    }

    @Test
    void testExistByModel() {
        boolean result = loanStaffServiceImpl.existByModel(new Loan());
        Assertions.assertEquals(true, result);
    }

    @Test
    void testListLoanByDate() {
        when(loanDAO.findByDateExpirationLessThanAndReturnedIsFalse(any())).thenReturn(Arrays.<Loan>asList(new Loan()));

        List<Loan> result = loanStaffServiceImpl.listLoanByDate();
        Assertions.assertEquals(Arrays.<Loan>asList(new Loan()), result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme