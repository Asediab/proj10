package com.biblio.client.service;

import com.biblio.client.DTO.LoanDTO;
import org.springframework.data.domain.Page;

public interface LoanService {

    Page<LoanDTO> loansByUser(Long userId);

    void prolongateLoan(Long loanId);
}
