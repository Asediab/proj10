package com.biblio.client.service.impl;

import com.biblio.client.DTO.LoanDTO;
import com.biblio.client.proxy.MicroserviceDocumentProxy;
import com.biblio.client.proxy.MicroserviceLoanProxy;
import com.biblio.client.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LoanServiceImpl implements LoanService {

    @Autowired
    private MicroserviceLoanProxy loanProxy;

    @Autowired
    private MicroserviceDocumentProxy documentProxy;

    @Override
    public Page<LoanDTO> loansByUser(Pageable pageable, Long userId) {

        List<LoanDTO> listLoansByUser = loanProxy.listLoansByUser(userId);

        for (LoanDTO loan : listLoansByUser) {
            loan.setCopyOfDocumentDTO(documentProxy.getDocumentByID(loan.getCopyOfDocumentId()));
        }

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), listLoansByUser.size());
        Page<LoanDTO> pages = new PageImpl<LoanDTO>(listLoansByUser.subList(start, end), pageable, listLoansByUser.size());

        return pages;
    }


    @Override
    public void prolongateLoan(Long loanId) {
        loanProxy.prolongateLoanPeriod(loanId);
    }

    @Override
    public LoanDTO dateExpirationLoanByDocumentId(Long documentId) {
        List<LoanDTO> loan = loanProxy.listLoansByDocumentId(documentId);
        if (!loan.isEmpty()) {
            return loan.get(0);
        } else {
            LoanDTO loanDTO = new LoanDTO();
            loanDTO.setDateExpiration(LocalDate.now());
            return loanDTO;
        }
    }
}
