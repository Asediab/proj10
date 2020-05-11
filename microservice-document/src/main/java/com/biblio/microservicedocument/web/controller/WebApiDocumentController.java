package com.biblio.microservicedocument.web.controller;

import com.biblio.microservicedocument.model.CopyOfDocument;
import com.biblio.microservicedocument.model.Document;
import com.biblio.microservicedocument.service.CopyOfDocumentService;
import com.biblio.microservicedocument.service.DocumentService;
import com.biblio.microservicedocument.web.exceptions.DocumentsNotFoundException;
import com.biblio.microservicedocument.web.exceptions.SearchOptionsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WebApiDocumentController {

    @Autowired
    private DocumentService documentService;

    @Autowired
    private CopyOfDocumentService copyOfDocumentService;

    @GetMapping(value = "/documents/")
    public List<Document> listDocuments() {
        List<Document> docsList = documentService.findAll();
        if (docsList.isEmpty()) {
            throw new DocumentsNotFoundException("The database of documents is empty");
        }
        return docsList;
    }

    @GetMapping(value = "/documents/search")
    public List<Document> searchDocuments(@RequestParam(name = "titre", value = "", required = true) String titre,
                                          @RequestParam(name = "author", value = "", required = true) String author) throws SearchOptionsException {
        if (titre.isEmpty() & author.isEmpty()) {
            throw new SearchOptionsException("No search parameters set. The following options are available: title, author.");
        }
        List<Document> docsSearchList = documentService.filter(titre, author);
        if (docsSearchList.isEmpty()) {
            throw new DocumentsNotFoundException("The database of documents is empty");
        }
        return docsSearchList;
    }

    @GetMapping(value = "/documents/{idCopyDoc}")
    public CopyOfDocument getDocumentByID(@PathVariable("idCopyDoc") Long docCopyID) throws SearchOptionsException {
        CopyOfDocument doc = copyOfDocumentService.findById(docCopyID);
        if (doc == null) {
            throw new DocumentsNotFoundException("Wrong CopyOfDocumentID");
        }
        return doc;
    }
}
