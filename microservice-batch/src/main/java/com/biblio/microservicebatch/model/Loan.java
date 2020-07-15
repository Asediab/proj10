package com.biblio.microservicebatch.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Loan implements Serializable {

    private Long id;

    private Long userId;

    private User user;

    private Long copyOfDocumentId;

    private CopyOfDocument copyOfDocument;

    private int numberOfRenewals;

    private LocalDate dateCreation;

    private LocalDate dateExpiration;

    private boolean returned;

    private Long documentId;

    public Loan() {
    }

    public User getUser() {
        return user;
    }

    public CopyOfDocument getCopyOfDocument() {
        return copyOfDocument;
    }

    public void setCopyOfDocument(CopyOfDocument copyOfDocument) {
        this.copyOfDocument = copyOfDocument;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCopyOfDocumentId() {
        return copyOfDocumentId;
    }

    public void setCopyOfDocumentId(Long copyOfDocumentId) {
        this.copyOfDocumentId = copyOfDocumentId;
    }

    public int getNumberOfRenewals() {
        return numberOfRenewals;
    }

    public void setNumberOfRenewals(int numberOfRenewals) {
        this.numberOfRenewals = numberOfRenewals;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public LocalDate getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(LocalDate dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }

    public Long getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "id=" + id +
                ", userId=" + userId +
                ", user=" + user +
                ", copyOfDocumentId=" + copyOfDocumentId +
                ", copyOfDocument=" + copyOfDocument +
                ", numberOfRenewals=" + numberOfRenewals +
                ", dateCreation=" + dateCreation +
                ", dateExpiration=" + dateExpiration +
                ", returned=" + returned +
                ", documentId=" + documentId +
                '}';
    }
}
