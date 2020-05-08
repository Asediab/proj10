package com.biblio.microservicedocument.service;

import com.biblio.microservicedocument.dao.CopyOfDocumentDAO;
import com.biblio.microservicedocument.model.CopyOfDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CopyOfDocumentServiceImpl implements CopyOfDocumentService {

    @Autowired
    private CopyOfDocumentDAO copyOfDocumentDAO;

    @Override
    public CopyOfDocument getById(Long id) {
        return copyOfDocumentDAO.getOne(id);
    }

    @Override
    public CopyOfDocument findById(Long id) {
        return copyOfDocumentDAO.findById(id).orElse(null);
    }
}
