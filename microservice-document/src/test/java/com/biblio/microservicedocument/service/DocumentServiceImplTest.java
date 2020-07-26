package com.biblio.microservicedocument.service;

import com.biblio.microservicedocument.dao.DocumentDAO;
import com.biblio.microservicedocument.model.Document;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class DocumentServiceImplTest {
    @Mock
    DocumentDAO documentDAO;
    @InjectMocks
    DocumentServiceImpl documentServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFilter() {
        List<Document> result = documentServiceImpl.filter("titre", "author");
        Assertions.assertEquals(Arrays.<Document>asList(new Document()), result);
    }

    @Test
    void testFindAll() {
        when(documentDAO.findAll()).thenReturn(Arrays.<Document>asList(new Document()));

        List<Document> result = documentServiceImpl.findAll();
        Assertions.assertEquals(Arrays.<Document>asList(new Document()), result);
    }

    @Test
    void testGetOne() {
        Document result = documentServiceImpl.getOne(Long.valueOf(1));
        Assertions.assertEquals(new Document(), result);
    }

    @Test
    void testAddReservation() {
        Document result = documentServiceImpl.addReservation(Long.valueOf(1));
        Assertions.assertEquals(new Document(), result);
    }

    @Test
    void testDeleteReservation() {
        Document result = documentServiceImpl.deleteReservation(Long.valueOf(1));
        Assertions.assertEquals(new Document(), result);
    }

    @Test
    void testFindById() {
        Document result = documentServiceImpl.findById(Long.valueOf(1));
        Assertions.assertEquals(new Document(), result);
    }

    @Test
    void testAddCopyAvailable() {
        Document result = documentServiceImpl.addCopyAvailable(Long.valueOf(1));
        Assertions.assertEquals(new Document(), result);
    }

    @Test
    void testDeleteCopyAvailable() {
        Document result = documentServiceImpl.deleteCopyAvailable(Long.valueOf(1));
        Assertions.assertEquals(new Document(), result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme