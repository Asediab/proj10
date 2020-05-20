package com.biblio.microservicedocument.model;

import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Positive;

@Entity
public class CopyOfDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "document_id", nullable = false)
    private Document document;

    @Positive
    @Column(nullable = false)
    private Long serialNumber;

    @ManyToOne
    @JoinColumn(name = "library_id", nullable = false)
    private Library library;

    private boolean available;

    public CopyOfDocument() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NonNull
    public Document getDocument() {
        return document;
    }

    public void setDocument(@NonNull Document document) {
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

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "CopyOfDocument{" +
                "id=" + id +
                ", document=" + document +
                ", serialNumber=" + serialNumber +
                ", library=" + library +
                ", available=" + available +
                '}';
    }
}
