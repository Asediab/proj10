package com.biblio.microservicebatch.model;

import java.io.Serializable;

public class CopyOfDocument implements Serializable {

    private Long id;

    private Document document;

    private Long serialNumber;

    private Library library;

    public CopyOfDocument() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public Long getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Long serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    @Override
    public String toString() {
        return "CopyOfDocument{" +
                "id=" + id +
                ", document=" + document +
                ", serialNumber=" + serialNumber +
                ", library=" + library +
                '}';
    }
}
