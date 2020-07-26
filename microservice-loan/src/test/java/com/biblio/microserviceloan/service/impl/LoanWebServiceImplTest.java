package com.biblio.microserviceloan.service.impl;

import com.biblio.microserviceloan.dao.LoanDAO;
import com.biblio.microserviceloan.model.Loan;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class LoanWebServiceImplTest {
    @Mock
    LoanDAO loanDAO;
    @InjectMocks
    LoanWebServiceImpl loanWebServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetOne() {
        when(loanDAO.getOne(anyLong())).thenReturn(new Loan());

        Loan result = loanWebServiceImpl.getOne(Long.valueOf(1));
        Assertions.assertEquals(new Loan(), result);
    }

    @Test
    void testFindByUserIdAndReturnedIsFalseOrderByDateCreationAsc() {
        when(loanDAO.findByUserIdAndReturnedIsFalseOrderByDateCreationAsc(anyLong())).thenReturn(Arrays.<Loan>asList(new Loan()));

        List<Loan> result = loanWebServiceImpl.findByUserIdAndReturnedIsFalseOrderByDateCreationAsc(Long.valueOf(1));
        Assertions.assertEquals(Arrays.<Loan>asList(new Loan()), result);
    }

    @Test
    void testModifyDateExpiration() {
        boolean result = loanWebServiceImpl.modifyDateExpiration(new Loan());
        Assertions.assertEquals(true, result);
    }

    @Test
    void testFindByDocumentIdAndSortByDateExpirationAsc() {
        when(loanDAO.findByDocumentIdOrderByDateExpirationAsc(anyLong())).thenReturn(Arrays.<Loan>asList(new Loan()));

        List<Loan> result = loanWebServiceImpl.findByDocumentIdAndSortByDateExpirationAsc(Long.valueOf(1));
        Assertions.assertEquals(Arrays.<Loan>asList(new Loan()), result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme