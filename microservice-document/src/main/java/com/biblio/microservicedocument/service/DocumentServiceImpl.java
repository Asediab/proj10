package com.biblio.microservicedocument.service;

import com.biblio.microservicedocument.dao.DocumentDAO;
import com.biblio.microservicedocument.model.Document;
import com.biblio.microservicedocument.model.QDocument;
import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private DocumentDAO documentDAO;

    @Override
    public List<Document> filter(String titre, String author) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        final QDocument qDocument = QDocument.document;
        if (!titre.isEmpty()) {
            booleanBuilder.and(qDocument.titre.containsIgnoreCase(titre));
        }
        if (!author.isEmpty()) {
            booleanBuilder.and(qDocument.author.containsIgnoreCase(author));
        }
        return (List<Document>) documentDAO.findAll(booleanBuilder);
    }

    @Override
    public List<Document> findAll() {
        return documentDAO.findAll();
    }
}
