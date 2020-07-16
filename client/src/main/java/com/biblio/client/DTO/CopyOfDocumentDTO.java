package com.biblio.client.DTO;


import java.io.Serializable;

public class CopyOfDocumentDTO implements Serializable {

    private Long id;

    private DocumentDTO document;

    private Long serialNumber;

    private LibraryDTO library;

    public CopyOfDocumentDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DocumentDTO getDocument() {
        return document;
    }

    public void setDocument(DocumentDTO document) {
        this.document = document;
    }

    public Long getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Long serialNumber) {
        this.serialNumber = serialNumber;
    }

    public LibraryDTO getLibrary() {
        return library;
    }

    public void setLibrary(LibraryDTO library) {
        this.library = library;
    }

    @Override
    public String toString() {
        return "CopyOfDocumentDTO{" +
                "id=" + id +
                ", document=" + document +
                ", serialNumber=" + serialNumber +
                ", library=" + library +
                '}';
    }
}
