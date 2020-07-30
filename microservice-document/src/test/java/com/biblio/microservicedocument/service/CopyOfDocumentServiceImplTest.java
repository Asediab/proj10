package com.biblio.microservicedocument.service;

import com.biblio.microservicedocument.dao.CopyOfDocumentDAO;
import com.biblio.microservicedocument.dao.DocumentDAO;
import com.biblio.microservicedocument.model.CopyOfDocument;
import com.biblio.microservicedocument.model.Document;
import com.biblio.microservicedocument.web.exceptions.DocumentsNotFoundException;
import org.junit.jupiter.api.*;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;

class CopyOfDocumentServiceImplTest {
    @Mock
    CopyOfDocumentDAO copyOfDocumentDAO = mock(CopyOfDocumentDAO.class);
    @Mock
    DocumentDAO documentDAO = mock(DocumentDAO.class);
    @InjectMocks
    CopyOfDocumentServiceImpl copyOfDocumentServiceImpl = new CopyOfDocumentServiceImpl(copyOfDocumentDAO, documentDAO);

    static CopyOfDocument copyOfDocumentTest = new CopyOfDocument();

    @BeforeAll
    static void beforeAll() {
        copyOfDocumentTest.setId(1L);
        copyOfDocumentTest.setAvailable(Boolean.FALSE);
        copyOfDocumentTest.setSerialNumber(21548556L);
        copyOfDocumentTest.setDocument(new Document());
    }

    @Test
    @Tag("getById")
    @DisplayName("CopyOfDocument ID is correct")
    void testGetById_idIsCorrect() {
        when(copyOfDocumentDAO.getOne(1L)).thenReturn(copyOfDocumentTest);

        Assertions.assertEquals(copyOfDocumentServiceImpl.getById(1L), copyOfDocumentTest);
        verify(copyOfDocumentDAO).getOne(1L);

    }

    @Test
    @Tag("getById")
    @DisplayName("CopyOfDocument ID is not correct")
    void testGetById_idIsNotCorrect() {
        DocumentsNotFoundException exception = new DocumentsNotFoundException("Id is not correct");
        when(copyOfDocumentDAO.getOne(-25L)).thenThrow(exception);

        Assertions.assertThrows(DocumentsNotFoundException.class, () -> {
            copyOfDocumentServiceImpl.getById(-25L);
        });

        verify(copyOfDocumentDAO).getOne(-25L);
    }

    @Test
    @Tag("findById")
    @DisplayName("CopyOfDocument ID is correct")
    void testFindById_idIsCorrect() {
        when(copyOfDocumentDAO.findById(anyLong())).thenReturn(Optional.ofNullable(copyOfDocumentTest));

        Assertions.assertEquals(copyOfDocumentServiceImpl.findById(1L), copyOfDocumentTest);
        verify(copyOfDocumentDAO).findById(1L);
    }

    @Test
    @Tag("findById")
    @DisplayName("CopyOfDocument ID is not correct")
    void testFindById_idIsNotCorrect() {
        DocumentsNotFoundException exception = new DocumentsNotFoundException("Id is not correct");
        when(copyOfDocumentDAO.findById(-25L)).thenThrow(exception);

        Assertions.assertThrows(DocumentsNotFoundException.class, () -> {
            copyOfDocumentServiceImpl.findById(-25L);
        });

        verify(copyOfDocumentDAO).findById(-25L);
    }

    @Test
    @Tag("refreshNumbrAvailableDoc")
    @DisplayName("RefreshNumbrAvailableDoc")
    void testRefreshNumbrAvailableDoc() {
        Document doc = new Document();
        doc.setId(1L);
        when(copyOfDocumentDAO.findByDocumentAndAvailableIsTrue(any(Document.class))).thenReturn(Arrays.<CopyOfDocument>asList(new CopyOfDocument(), new CopyOfDocument(), new CopyOfDocument(), new CopyOfDocument()));
        when(documentDAO.getOne(anyLong())).thenReturn(doc);
        when(documentDAO.save(any(Document.class))).thenReturn(doc);

        copyOfDocumentServiceImpl.refreshNumbrAvailableDoc(doc);

        Assertions.assertEquals(doc.getCopyAvailable(), 4);

    }
}
