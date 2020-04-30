package com.biblio.microservicedocument.service;

import com.biblio.microservicedocument.model.Document;

import java.util.List;

public interface DocumentService {

    List<Document> filter(String titre, String author);

    List<Document> findAll();
}
