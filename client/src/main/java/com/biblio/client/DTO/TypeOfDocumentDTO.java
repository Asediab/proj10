package com.biblio.client.DTO;

import java.io.Serializable;

public class TypeOfDocumentDTO implements Serializable {

    private Long id;

    private String type;

    public TypeOfDocumentDTO() {
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
}
