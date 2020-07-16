package com.biblio.microservicedocument.web.controller;

import com.biblio.microservicedocument.model.CopyOfDocument;
import com.biblio.microservicedocument.model.Document;
import com.biblio.microservicedocument.service.CopyOfDocumentService;
import com.biblio.microservicedocument.service.DocumentService;
import com.biblio.microservicedocument.web.exceptions.DocumentsNotFoundException;
import com.biblio.microservicedocument.web.exceptions.SearchOptionsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @GetMapping(value = "/documents/API/{idDoc}")
    public Document getDocByID(@PathVariable("idDoc") Long docID) throws SearchOptionsException {
        Document doc = documentService.findById(docID);
        if (doc == null) {
            throw new DocumentsNotFoundException("Wrong DocumentID");
        }
        return doc;
    }



    @PutMapping(value = "/documents/api")
    public ResponseEntity<Void> returnDoc(@RequestBody @Valid CopyOfDocument copyOfDocument) {
        if (copyOfDocumentService.getById(copyOfDocument.getId())==null) {
            throw new DocumentsNotFoundException("CopyOfDocument not exist");
        }
        copyOfDocument.setAvailable(Boolean.TRUE);
        copyOfDocumentService.refreshNumbrAvailableDoc(copyOfDocument.getDocument());

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping(value = "/documents/api")
    public ResponseEntity<Void> loanDoc(@RequestBody @Valid CopyOfDocument copyOfDocument) {
        if (copyOfDocumentService.getById(copyOfDocument.getId())==null) {
            throw new DocumentsNotFoundException("CopyOfDocument not exist");
        }
        copyOfDocument.setAvailable(Boolean.FALSE);
        copyOfDocumentService.refreshNumbrAvailableDoc(copyOfDocument.getDocument());

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping(value = "/documents/API/addReservation/{idDoc}")
    public ResponseEntity<Void> addReservation(@PathVariable("idDoc") Long docID) {
        if (documentService.addReservation(docID) == null) {
            throw new DocumentsNotFoundException("Document not exist");
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping(value = "/documents/API/deleteReservation/{idDoc}")
    public ResponseEntity<Void> deleteReservation(@PathVariable("idDoc") Long docID) {
        if (documentService.deleteReservation(docID)==null) {
            throw new DocumentsNotFoundException("Document not exist");
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
