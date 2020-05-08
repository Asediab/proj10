package com.biblio.client.DTO;

import java.time.LocalDate;

public class LoanDTO {

    private Long id;

    private Long userId;

    private Long copyOfDocumentId;

    private int numberOfRenewals;

    private LocalDate dateCreation;

    private LocalDate dateExpiration;

    private boolean returned;

    public LoanDTO() {
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
        return "LoanDTO{" +
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
