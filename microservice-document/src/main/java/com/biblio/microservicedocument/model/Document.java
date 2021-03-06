package com.biblio.microservicedocument.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Document implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "typeOfDocument_id", nullable = false)
    private TypeOfDocument typeOfDocument;

    @NotNull
    @Column(nullable = false)
    private LocalDate yearOsIssue;

    @NotNull
    @Column(nullable = false)
    private String titre;

    @Positive
    @Column(nullable = false)
    private int numberOfPages;

    @NotBlank
    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private int copyAvailable;

    @Column(nullable = false)
    private String photo;

    @Column(nullable = false)
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "document", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<CopyOfDocument> copyOfDocumentList;

    @Column(nullable = false)
    private int reservations;

    @Column(nullable = false)
    private int copyTotal;

    public Document() {
    }

    public int getReservations() {
        return reservations;
    }

    public void setReservations(int reservations) {
        this.reservations = reservations;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TypeOfDocument getTypeOfDocument() {
        return typeOfDocument;
    }

    public void setTypeOfDocument(TypeOfDocument typeOfDocument) {
        this.typeOfDocument = typeOfDocument;
    }

    public LocalDate getYearOsIssue() {
        return yearOsIssue;
    }

    public void setYearOsIssue(LocalDate yearOsIssue) {
        this.yearOsIssue = yearOsIssue;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<CopyOfDocument> getCopyOfDocumentList() {
        return copyOfDocumentList;
    }

    public void setCopyOfDocumentList(List<CopyOfDocument> copyOfDocumentList) {
        this.copyOfDocumentList = copyOfDocumentList;
    }

    public int getCopyAvailable() {
        return copyAvailable;
    }

    public void setCopyAvailable(int copyAvailable) {
        this.copyAvailable = copyAvailable;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCopyTotal() {
        return copyTotal;
    }

    public void setCopyTotal(int copyTotal) {
        this.copyTotal = copyTotal;
    }

    @Override
    public String toString() {
        return "Document{" +
                "id=" + id +
                ", typeOfDocument=" + typeOfDocument +
                ", yearOsIssue=" + yearOsIssue +
                ", titre='" + titre + '\'' +
                ", numberOfPages=" + numberOfPages +
                ", author='" + author + '\'' +
                ", copyAvailable=" + copyAvailable +
                ", photo='" + photo + '\'' +
                ", description='" + description + '\'' +
                ", copyOfDocumentList=" + copyOfDocumentList +
                ", reservations=" + reservations +
                ", copyTotal=" + copyTotal +
                '}';
    }
}
