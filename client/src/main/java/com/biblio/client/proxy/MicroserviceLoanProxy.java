package com.biblio.client.proxy;

import com.biblio.client.DTO.LoanDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;


//@FeignClient(name = "zuul-server")
//@RibbonClient(name = "microservice-loan")


@FeignClient(name = "microservice-loan", configuration = AccountClientConfiguration.class)
public interface MicroserviceLoanProxy {

    @GetMapping(value = "/microservice-loan/loans/{userId}")
    List<LoanDTO> listLoansByUser(@PathVariable("userId") Long userId);

    @PutMapping(value = "/microservice-loan/loans/prolongateLoan/{loanID}")
    ResponseEntity<Void> prolongateLoanPeriod(@PathVariable("loanID") Long loanID);
}
