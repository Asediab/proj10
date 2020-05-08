package com.biblio.microservicedocument.dao;

import com.biblio.microservicedocument.model.CopyOfDocument;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CopyOfDocumentDAO extends JpaRepository<CopyOfDocument, Long> {
}
