package com.biblio.client.DTO;

import java.time.LocalDate;

public class LoanDTO {

    private Long id;

    private Long userId;

    private UserDTO user;

    private Long copyOfDocumentId;

    private CopyOfDocumentDTO copyOfDocumentDTO;

    private int numberOfRenewals;

    private LocalDate dateCreation;

    private LocalDate dateExpiration;

    private boolean returned;

    private Long documentId;

    public LoanDTO() {
    }

    public CopyOfDocumentDTO getCopyOfDocumentDTO() {
        return copyOfDocumentDTO;
    }

    public void setCopyOfDocumentDTO(CopyOfDocumentDTO copyOfDocumentDTO) {
        this.copyOfDocumentDTO = copyOfDocumentDTO;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
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
        return "LoanDTO{" +
                "id=" + id +
                ", userId=" + userId +
                ", user=" + user +
                ", copyOfDocumentId=" + copyOfDocumentId +
                ", copyOfDocumentDTO=" + copyOfDocumentDTO +
                ", numberOfRenewals=" + numberOfRenewals +
                ", dateCreation=" + dateCreation +
                ", dateExpiration=" + dateExpiration +
                ", returned=" + returned +
                ", documentId=" + documentId +
                '}';
    }
}
