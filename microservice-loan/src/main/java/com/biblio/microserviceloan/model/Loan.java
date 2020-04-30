package com.biblio.microserviceloan.model;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Entity
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Positive
    @Column(nullable = false)
    private Long userId;

    @Positive
    @Column(nullable = false)
    private Long copyOfDocumentId;

    @Column(nullable = false)
    private int numberOfRenewals;

    @Column(nullable = false)
    private LocalDate dateCreation;

    @Column(nullable = false)
    private LocalDate dateExpiration;

    @Column(nullable = false)
    private boolean returned;

    public Loan() {
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

    @Override
    public String toString() {
        return "Loan{" +
                "id=" + id +
                ", userId=" + userId +
                ", copyOfDocumentId=" + copyOfDocumentId +
                ", numberOfRenewals=" + numberOfRenewals +
                ", dateCreation=" + dateCreation +
                ", dateExpiration=" + dateExpiration +
                ", returned=" + returned +
                '}';
    }
}
