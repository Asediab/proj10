package com.biblio.client.service;

import com.biblio.client.DTO.CopyOfDocumentDTO;
import com.biblio.client.DTO.DocumentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DocumentService {

    Page<DocumentDTO> getDocuments(Pageable pageable, String author, String title);

    CopyOfDocumentDTO getDocumentById(Long id);

}
