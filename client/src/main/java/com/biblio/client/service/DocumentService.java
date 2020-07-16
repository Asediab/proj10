package com.biblio.client.service;

import com.biblio.client.DTO.CopyOfDocumentDTO;
import com.biblio.client.DTO.DocumentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

public interface DocumentService {

    Page<DocumentDTO> getDocuments(Pageable pageable, String author, String title, OAuth2Authentication principal);

    CopyOfDocumentDTO getDocumentById(Long id);

}
