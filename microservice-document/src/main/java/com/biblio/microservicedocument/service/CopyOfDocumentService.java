package com.biblio.microservicedocument.service;

import com.biblio.microservicedocument.model.CopyOfDocument;

public interface CopyOfDocumentService {

    CopyOfDocument getById(Long id);

    CopyOfDocument findById(Long id);
}
