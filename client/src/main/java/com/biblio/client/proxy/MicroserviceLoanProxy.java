package com.biblio.client.proxy;

import com.biblio.client.DTO.LoanDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.Collections;
import java.util.List;


//@FeignClient(name = "zuul-server")
//@RibbonClient(name = "microservice-loan")


@FeignClient(name = "microservice-loan", fallback = MicroserviceLoanProxy.LoanFallback.class)
public interface MicroserviceLoanProxy {

    @GetMapping(value = "/loans/{userId}")
    List<LoanDTO> listLoansByUser(@PathVariable("userId") Long userId);

    @PutMapping(value = "/loans/prolongateLoan/{loanID}")
    ResponseEntity<Void> prolongateLoanPeriod(@PathVariable("loanID") Long loanID);


    class LoanFallback implements MicroserviceLoanProxy {

        @Override
        public List<LoanDTO> listLoansByUser(Long userId) {
            return Collections.emptyList();
        }

        @Override
        public ResponseEntity<Void> prolongateLoanPeriod(Long loanID) {
            return null;
        }
    }
}
