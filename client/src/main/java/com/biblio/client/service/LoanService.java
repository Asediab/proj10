package com.biblio.client.service;

import com.biblio.client.DTO.LoanDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LoanService {

    Page<LoanDTO> loansByUser(Pageable pageable, Long userId);

    void prolongateLoan(Long loanId);

    LoanDTO dateExpirationLoanByDocumentId(Long documentId);
}
