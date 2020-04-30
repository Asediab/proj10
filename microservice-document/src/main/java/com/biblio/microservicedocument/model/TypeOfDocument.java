package com.biblio.microservicedocument.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class TypeOfDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String type;

    public TypeOfDocument() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "TypeOfDocument{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }
}
