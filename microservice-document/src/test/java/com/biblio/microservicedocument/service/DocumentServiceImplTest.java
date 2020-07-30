package com.biblio.microservicedocument.service;

import com.biblio.microservicedocument.dao.DocumentDAO;
import com.biblio.microservicedocument.model.CopyOfDocument;
import com.biblio.microservicedocument.model.Document;
import com.biblio.microservicedocument.web.exceptions.DocumentsNotFoundException;
import com.querydsl.core.types.Predicate;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.mockito.Mockito.*;

class DocumentServiceImplTest {
    @Mock
    DocumentDAO documentDAO = mock(DocumentDAO.class);
    @InjectMocks
    DocumentServiceImpl documentServiceImpl = new DocumentServiceImpl(documentDAO);

    static Document doc1 = new Document();
    static Document doc2 = new Document();

@BeforeAll
static void beforeAll() {
    doc1.setId(1L);
    doc1.setAuthor("ABC");
    doc1.setTitre("ABC");
    doc2.setId(2L);
    doc2.setAuthor("BCA");
    doc2.setTitre("BCA");
}

    @Test
    @Tag("filter")
    @DisplayName("Filter by Titre and Author")
    void testFilter() {
        when(documentDAO.findAll((Predicate) any())).thenReturn(Collections.singletonList(doc1));

        Assertions.assertEquals(Collections.singletonList(doc1), documentServiceImpl.filter("ABC", "ABC"));
        Assertions.assertNotEquals(Collections.singletonList(doc2), documentServiceImpl.filter("ABC", "ABC"));
        Assertions.assertEquals(Collections.singletonList(doc1), documentServiceImpl.filter("ABC", ""));


    }

    @Test
    @Tag("findAll")
    @DisplayName("FindAll documents ")
    void testFindAll() {
        when(documentDAO.findAll()).thenReturn(Arrays.asList(doc1, doc2));

        List<Document> result = documentServiceImpl.findAll();

        Assertions.assertEquals(result, Arrays.asList(doc1, doc2));
        Assertions.assertNotNull(result);
        Assertions.assertNotEquals(result, Arrays.asList(new Document()));
    }

    @Test
    @Tag("getOne")
    @DisplayName("GetOne by correct documentId")
    void testGetOne() {
        when(documentDAO.getOne(anyLong())).thenReturn(doc1);

        Assertions.assertEquals(doc1, documentServiceImpl.getOne(1L));
        Assertions.assertNotNull(documentServiceImpl.getOne(1L));
    }

    @Test
    @Tag("getOne")
    @DisplayName("GetOne by no correct documentId")
    void testGetOne_documentIdNotCorrect() {
        DocumentsNotFoundException exception = new DocumentsNotFoundException("Id is not correct");
        when(documentDAO.getOne(-25L)).thenThrow(exception);

        Assertions.assertThrows(DocumentsNotFoundException.class, () -> {
            documentServiceImpl.getOne(-25L);
        });

        verify(documentDAO).getOne(-25L);
    }

    @Test
    @Tag("findById")
    @DisplayName("findById CopyOfDocumentID is correct")
    void testFindById() {
        when(documentDAO.findById(anyLong())).thenReturn(Optional.ofNullable(doc1));

        Assertions.assertEquals(doc1, documentServiceImpl.findById(1L));
        verify(documentDAO).findById(1L);
    }

    @Test
    @Tag("findById")
    @DisplayName("findById CopyOfDocumentID is not correct")
    void testFindById_idNotCorrect() {
        DocumentsNotFoundException exception = new DocumentsNotFoundException("Id is not correct");
        when(documentDAO.findById(-25L)).thenThrow(exception);

        Assertions.assertThrows(DocumentsNotFoundException.class, () -> {
            documentServiceImpl.findById(-25L);
        });

        verify(documentDAO).findById(-25L);
    }

    @Test
    @Tag("addCopyAvailable")
    @DisplayName("Test AddCopyAvailable")
    void testAddCopyAvailable() {
        doc1.setCopyAvailable(0);
        when(documentDAO.getOne(anyLong())).thenReturn(doc1);
        when(documentDAO.save(any(Document.class))).thenReturn(doc1);

        Document actual = documentServiceImpl.addCopyAvailable(doc1.getId());

        Assertions.assertEquals(1, actual.getCopyAvailable());
        Assertions.assertNotEquals(0, actual.getCopyAvailable());

    }

    @Test
    @Tag("deleteCopyAvailable")
    @DisplayName("Test DeleteCopyAvailable")
    void testDeleteCopyAvailable() {
        doc1.setCopyAvailable(10);
        when(documentDAO.getOne(anyLong())).thenReturn(doc1);
        when(documentDAO.save(any(Document.class))).thenReturn(doc1);

        Document actual = documentServiceImpl.deleteCopyAvailable(doc1.getId());

        Assertions.assertEquals(9, actual.getCopyAvailable());
        Assertions.assertNotEquals(10, actual.getCopyAvailable());
    }
}