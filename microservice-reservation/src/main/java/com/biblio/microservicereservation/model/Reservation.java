package com.biblio.microservicereservation.model;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Positive
    @Column(nullable = false)
    private Long documentId;

    private LocalDate dateCreation;

    private LocalDate dateExpiration;

    @Positive
    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String userSurname;

    @Column(nullable = false)
    private String userEmail;

    private boolean isActive;

    private boolean isMailSent;

    private boolean isTakenByUser;

    private LocalDate mailSentDate;

    private LocalDate mailExpirationDate;

    @Column(nullable = false)
    private String documentName;


    public Reservation() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isMailSent() {
        return isMailSent;
    }

    public void setMailSent(boolean mailSent) {
        isMailSent = mailSent;
    }

    public boolean isTakenByUser() {
        return isTakenByUser;
    }

    public void setTakenByUser(boolean takenByUser) {
        isTakenByUser = takenByUser;
    }

    public LocalDate getMailSentDate() {
        return mailSentDate;
    }

    public void setMailSentDate(LocalDate mailSentDate) {
        this.mailSentDate = mailSentDate;
    }

    public LocalDate getMailExpirationDate() {
        return mailExpirationDate;
    }

    public void setMailExpirationDate(LocalDate mailExpirationDate) {
        this.mailExpirationDate = mailExpirationDate;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", documentId=" + documentId +
                ", dateCreation=" + dateCreation +
                ", dateExpiration=" + dateExpiration +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userSurname='" + userSurname + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", isActive=" + isActive +
                ", isMailSent=" + isMailSent +
                ", isTakenByUser=" + isTakenByUser +
                ", mailSentDate=" + mailSentDate +
                ", mailExpirationDate=" + mailExpirationDate +
                ", documentName='" + documentName + '\'' +
                '}';
    }
}
