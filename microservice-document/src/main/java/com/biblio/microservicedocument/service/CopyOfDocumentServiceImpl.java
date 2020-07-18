package com.biblio.microservicedocument.service;

import com.biblio.microservicedocument.dao.CopyOfDocumentDAO;
import com.biblio.microservicedocument.dao.DocumentDAO;
import com.biblio.microservicedocument.model.CopyOfDocument;
import com.biblio.microservicedocument.model.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CopyOfDocumentServiceImpl implements CopyOfDocumentService {

    @Autowired
    private CopyOfDocumentDAO copyOfDocumentDAO;

    @Autowired
    private DocumentDAO documentDAO;

    @Override
    public CopyOfDocument getById(Long id) {
        return copyOfDocumentDAO.getOne(id);
    }

    @Override
    public CopyOfDocument findById(Long id) {
        return copyOfDocumentDAO.findById(id).orElse(null);
    }

    @Override
    public void refreshNumbrAvailableDoc(Document document) {
        List<CopyOfDocument> list = copyOfDocumentDAO.findByDocumentAndAvailableIsTrue(document);
        Document doc = documentDAO.getOne(document.getId());
        doc.setCopyAvailable(list.size());
        documentDAO.save(doc);
    }
}
