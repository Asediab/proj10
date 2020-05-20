package com.biblio.microservicedocument.service;

import com.biblio.microservicedocument.model.CopyOfDocument;
import com.biblio.microservicedocument.model.Document;

public interface CopyOfDocumentService {

    CopyOfDocument getById(Long id);

    CopyOfDocument findById(Long id);

    void refreshNumbrAvailableDoc(Document document);
}
