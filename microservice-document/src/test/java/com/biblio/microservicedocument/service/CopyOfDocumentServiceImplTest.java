package com.biblio.microservicedocument.service;

import com.biblio.microservicedocument.dao.CopyOfDocumentDAO;
import com.biblio.microservicedocument.dao.DocumentDAO;
import com.biblio.microservicedocument.model.CopyOfDocument;
import com.biblio.microservicedocument.model.Document;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.mockito.Mockito.*;

class CopyOfDocumentServiceImplTest {
    @Mock
    CopyOfDocumentDAO copyOfDocumentDAO;
    @Mock
    DocumentDAO documentDAO;
    @InjectMocks
    CopyOfDocumentServiceImpl copyOfDocumentServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetById() {
        CopyOfDocument result = copyOfDocumentServiceImpl.getById(Long.valueOf(1));
        Assertions.assertEquals(new CopyOfDocument(), result);
    }

    @Test
    void testFindById() {
        CopyOfDocument result = copyOfDocumentServiceImpl.findById(Long.valueOf(1));
        Assertions.assertEquals(new CopyOfDocument(), result);
    }

    @Test
    void testRefreshNumbrAvailableDoc() {
        when(copyOfDocumentDAO.findByDocumentAndAvailableIsTrue(any())).thenReturn(Arrays.<CopyOfDocument>asList(new CopyOfDocument()));

        copyOfDocumentServiceImpl.refreshNumbrAvailableDoc(new Document());
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme