package com.biblio.client.service.impl;

import com.biblio.client.DTO.CopyOfDocumentDTO;
import com.biblio.client.DTO.DocumentDTO;
import com.biblio.client.DTO.LoanDTO;
import com.biblio.client.DTO.ReservationDTO;
import com.biblio.client.proxy.MicroserviceDocumentProxy;
import com.biblio.client.proxy.MicroserviceLoanProxy;
import com.biblio.client.proxy.MicroserviceReservationProxy;
import com.biblio.client.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private MicroserviceDocumentProxy documentProxy;

    @Autowired
    private MicroserviceLoanProxy loanProxy;

    @Override
    public Page<DocumentDTO> getDocuments(Pageable pageable, String author, String title, OAuth2Authentication principal) {
        List<DocumentDTO> documentDTOList;
        if (author.isEmpty() & title.isEmpty()) {
            documentDTOList = documentProxy.listDocuments();
        } else {
            documentDTOList = documentProxy.searchDocuments(title, author);
        }
        for (DocumentDTO dc : documentDTOList) {
            dc.setExpectedReturnDate(this.getReturnDate(dc));
            dc.setUserHaveLoanOfDoc(this.isUserHaveLoanForDocument(principal, dc.getId()));
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

    private LocalDate getReturnDate(DocumentDTO documentDTO) {
        List<LoanDTO> list = loanProxy.listLoansByDocumentId(documentDTO.getId());
        if (!list.isEmpty()) {
            list.sort(Comparator.comparing(LoanDTO::getDateExpiration));
            return list.get(0).getDateExpiration();
        }
        return LocalDate.now();
    }

    private boolean isUserHaveLoanForDocument(OAuth2Authentication principal, Long documentId) {
        try {
            LinkedHashMap map = (LinkedHashMap) principal.getUserAuthentication().getDetails();
            map = (LinkedHashMap) map.get("principal");
            long userId = (int) map.get("id");
            if (userId != 0L) {
                List<LoanDTO> loanDTOList = loanProxy.listLoansByUser(userId);
                for (LoanDTO loan : loanDTOList) {
                    return loan.getDocumentId().equals(documentId);
                }
            }
        } catch (NullPointerException ignored) {
        }
        return false;
    }
}
