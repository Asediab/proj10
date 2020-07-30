package com.biblio.microserviceloan.service.impl;

import com.biblio.microserviceloan.dao.LoanDAO;
import com.biblio.microserviceloan.model.Loan;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

class LoanWebServiceImplTest {
    @Mock
    LoanDAO loanDAO = mock(LoanDAO.class);
    @InjectMocks
    LoanWebServiceImpl loanWebServiceImpl = new LoanWebServiceImpl(loanDAO);

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
    @DisplayName("Test GetOne return Loan")
    void testGetOne() {
        when(loanDAO.getOne(anyLong())).thenReturn(loan1);

        Loan result = loanWebServiceImpl.getOne(1L);
        Assertions.assertEquals(loan1, result);
        Assertions.assertNotNull(result);
        Assertions.assertNotEquals(loan2, result);
    }

    @Test
    @Tag("findByUserIdAndReturnedIsFalseOrderByDateCreationAsc")
    @DisplayName("Test FindByUserIdAndReturnedIsFalseOrderByDateCreationAsc return List<Loan>")
    void testFindByUserIdAndReturnedIsFalseOrderByDateCreationAsc() {
        when(loanDAO.findByUserIdAndReturnedIsFalseOrderByDateCreationAsc(anyLong())).thenReturn(Arrays.asList(loan1, loan2));

        List<Loan> result = loanWebServiceImpl.findByUserIdAndReturnedIsFalseOrderByDateCreationAsc(1L);
        Assertions.assertEquals(Arrays.asList(loan1, loan2), result);
        Assertions.assertNotNull(result);
        Assertions.assertNotEquals(Collections.singletonList(loan2), result);
    }

    @Test
    @Tag("modifyDateExpiration")
    @DisplayName("Test ModifyDateExpiration return loan with modified DateExpiration")
    void testModifyDateExpiration() {
        loan1.setDateExpiration(LocalDate.now());
        when(loanDAO.save(any(Loan.class))).thenReturn(loan1);
        boolean result = loanWebServiceImpl.modifyDateExpiration(loan1);
        Assertions.assertTrue(result);
    }

    @Test
    @Tag("findByDocumentIdAndSortByDateExpirationAsc")
    @DisplayName("Test FindByDocumentIdAndSortByDateExpirationAsc return List<Loan>")
    void testFindByDocumentIdAndSortByDateExpirationAsc() {
        when(loanDAO.findByDocumentIdOrderByDateExpirationAsc(anyLong())).thenReturn(Arrays.asList(loan1, loan2));

        List<Loan> result = loanWebServiceImpl.findByDocumentIdAndSortByDateExpirationAsc(1L);
        Assertions.assertEquals(Arrays.asList(loan1, loan2), result);
        Assertions.assertNotNull(result);
        Assertions.assertNotEquals(Arrays.asList(loan1), result);


    }
}