package com.biblio.microservicebatch.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Document implements Serializable {

    private Long id;

    private LocalDate yearOsIssue;

    private String titre;

    private int numberOfPages;

    private String author;

    private int copyAvailable;

    private String photo;

    private String description;

    public Document() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Document{" +
                "id=" + id +
                ", yearOsIssue=" + yearOsIssue +
                ", titre='" + titre + '\'' +
                ", numberOfPages=" + numberOfPages +
                ", author='" + author + '\'' +
                ", copyAvailable=" + copyAvailable +
                ", photo='" + photo + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
