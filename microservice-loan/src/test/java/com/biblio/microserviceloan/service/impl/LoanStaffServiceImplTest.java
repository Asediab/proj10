package com.biblio.microserviceloan.service.impl;

import com.biblio.microserviceloan.dao.LoanDAO;
import com.biblio.microserviceloan.model.Loan;
import com.biblio.microserviceloan.proxy.MicroserviceDocumentProxy;
import com.biblio.microserviceloan.proxy.MicroserviceReservationProxy;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Example;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

class LoanStaffServiceImplTest {
    @Mock
    LoanDAO loanDAO = mock(LoanDAO.class);
    @Mock
    MicroserviceDocumentProxy documentProxy = mock(MicroserviceDocumentProxy.class);
    @Mock
    MicroserviceReservationProxy reservationProxy = mock(MicroserviceReservationProxy.class);
    @InjectMocks
    LoanStaffServiceImpl loanStaffServiceImpl = new LoanStaffServiceImpl(loanDAO, documentProxy, reservationProxy);

    static Loan loan1 = new Loan();
    static Loan loan2 = new Loan();

    @BeforeAll
    static void setUp() {
        loan1.setId(1L);
        loan1.setDateCreation(LocalDate.now());
        loan1.setDocumentId(1L);
        loan1.setUserId(1L);
        loan1.setCopyOfDocumentId(1L);

        loan2.setId(2L);
        loan2.setDateCreation(LocalDate.now());
        loan2.setDocumentId(2L);
        loan2.setUserId(2L);
        loan2.setCopyOfDocumentId(2L);
    }

    @Test
    @Tag("testGetOne")
    @DisplayName("Test GetOne")
    void testGetOne() {
        when(loanDAO.getOne(anyLong())).thenReturn(loan1);

        Loan result = loanStaffServiceImpl.getOne(1L);
        Assertions.assertEquals(loan1, result);
        Assertions.assertNotNull(result);
        Assertions.assertNotEquals(loan2, result);
    }

    @Test
    @Tag("testSaveNew")
    @DisplayName("Test SaveNew")
    void testSaveNew() {
        Loan loanNew = new Loan();
        loanNew.setId(3L);
        loanNew.setDateCreation(LocalDate.now());
        loanNew.setDateExpiration(loanNew.getDateCreation().plusDays(2));
        loanNew.setDocumentId(3L);
        loanNew.setUserId(3L);
        loanNew.setCopyOfDocumentId(1L);
        when(loanDAO.findByUserIdAndCopyOfDocumentIdAndReturnedIsFalse(anyLong(), anyLong())).thenReturn(null);
        when(documentProxy.deleteCopyAvailable(anyLong())).thenReturn(null);
        when(reservationProxy.deleteReservation(any())).thenReturn(null);
        when(loanDAO.save(any(Loan.class))).thenReturn(loanNew);

        Loan result = loanStaffServiceImpl.saveNew(loan1);
        Assertions.assertEquals(loanNew, result);
        Assertions.assertNotEquals(loan1, result);
        Assertions.assertNotNull(result);
    }

    @Test
    @Tag("testReturnLoan")
    @DisplayName("Test ReturnLoan")
    void testReturnLoan() {
        when(loanDAO.findByUserIdAndCopyOfDocumentIdAndReturnedIsFalse(anyLong(), anyLong())).thenReturn(loan1);
        when(documentProxy.addCopyAvailable(anyLong())).thenReturn(null);
        when(reservationProxy.sendMail(anyLong())).thenReturn(null);
        when(loanDAO.save(any(Loan.class))).thenReturn(loan1);

        Loan result = loanStaffServiceImpl.returnLoan(loan1);

        Assertions.assertEquals(Boolean.TRUE, result.isReturned());
        Assertions.assertNotEquals(Boolean.FALSE, result.isReturned());
    }

    @Test
    @Tag("testExistByModel")
    @DisplayName("Test ExistByModel")
    void testExistByModel() {
        when(loanDAO.exists(any())).thenReturn(true);
        boolean result = loanStaffServiceImpl.existByModel(loan1);
        Assertions.assertTrue(result);
    }

    @Test
    @Tag("testListLoanByDate")
    @DisplayName("Test ListLoanByDate")
    void testListLoanByDate() {
        when(loanDAO.findByDateExpirationLessThanAndReturnedIsFalse(any(LocalDate.class))).thenReturn(Arrays.asList(loan1, loan2));

        List<Loan> result = loanStaffServiceImpl.listLoanByDate();
        Assertions.assertEquals(Arrays.asList(loan1, loan2), result);
        Assertions.assertNotNull(result);
        Assertions.assertNotEquals(Collections.singletonList(loan1), result);
    }
}
