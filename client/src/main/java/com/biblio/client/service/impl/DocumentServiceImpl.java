package com.biblio.client.service.impl;

import com.biblio.client.DTO.CopyOfDocumentDTO;
import com.biblio.client.DTO.DocumentDTO;
import com.biblio.client.proxy.MicroserviceDocumentProxy;
import com.biblio.client.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private MicroserviceDocumentProxy documentProxy;

    @Override
    public Page<DocumentDTO> getDocuments(Pageable pageable, String author, String title) {
        List<DocumentDTO> documentDTOList;
        if (author.isEmpty() & title.isEmpty()) {
            documentDTOList = documentProxy.listDocuments();
        } else {
            documentDTOList = documentProxy.searchDocuments(title, author);
        }
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), documentDTOList.size());
        Page<DocumentDTO> pages = new PageImpl<DocumentDTO>(documentDTOList.subList(start, end), pageable, documentDTOList.size());

        return pages;
    }

    @Override
    public CopyOfDocumentDTO getDocumentById(Long copyOfDocumentId) {
        return documentProxy.getDocumentByID(copyOfDocumentId);
    }
}
