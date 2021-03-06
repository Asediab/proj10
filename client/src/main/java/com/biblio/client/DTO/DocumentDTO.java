package com.biblio.client.DTO;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.io.Serializable;
import java.time.LocalDate;

public class DocumentDTO implements Serializable {

    private Long id;

    private TypeOfDocumentDTO typeOfDocument;

    private LocalDate yearOsIssue;

    private String titre;

    private int numberOfPages;

    private String author;

    private int copyAvailable;

    private String photo;

    private String description;

    private int reservations;

    private int copyTotal;

    @JsonIgnore
    private LocalDate expectedReturnDate;

    @JsonIgnore
    private boolean isUserHaveLoanOfDoc;

    @JsonIgnore
    private boolean isUserHaveReservationOfDoc;


    public DocumentDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TypeOfDocumentDTO getTypeOfDocument() {
        return typeOfDocument;
    }

    public void setTypeOfDocument(TypeOfDocumentDTO typeOfDocument) {
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

    public int getReservations() {
        return reservations;
    }

    public void setReservations(int reservations) {
        this.reservations = reservations;
    }

    public int getCopyTotal() {
        return copyTotal;
    }

    public void setCopyTotal(int copyTotal) {
        this.copyTotal = copyTotal;
    }

    public LocalDate getExpectedReturnDate() {
        return expectedReturnDate;
    }

    public void setExpectedReturnDate(LocalDate expectedReturnDate) {
        this.expectedReturnDate = expectedReturnDate;
    }

    public boolean isUserHaveLoanOfDoc() {
        return isUserHaveLoanOfDoc;
    }

    public void setUserHaveLoanOfDoc(boolean userHaveLoanOfDoc) {
        isUserHaveLoanOfDoc = userHaveLoanOfDoc;
    }

    public boolean isUserHaveReservationOfDoc() {
        return isUserHaveReservationOfDoc;
    }

    public void setUserHaveReservationOfDoc(boolean userHaveReservationOfDoc) {
        isUserHaveReservationOfDoc = userHaveReservationOfDoc;
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
                ", reservations=" + reservations +
                ", copyTotal=" + copyTotal +
                '}';
    }
}
