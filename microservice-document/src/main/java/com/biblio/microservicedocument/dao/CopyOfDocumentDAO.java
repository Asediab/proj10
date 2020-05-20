package com.biblio.microservicedocument.dao;

import com.biblio.microservicedocument.model.CopyOfDocument;
import com.biblio.microservicedocument.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CopyOfDocumentDAO extends JpaRepository<CopyOfDocument, Long> {

    List<CopyOfDocument> findByDocumentAndAvailableIsTrue(Document document);
}
