package com.biblio.microservicebatch.proxy;

import com.biblio.microservicebatch.model.Loan;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@FeignClient(name = "microservice-loan", configuration = AccountClientConfiguration.class)
public interface MicroserviceLoanProxy {

    @GetMapping(value = "loans/staffApi/loansByDate")
    List<Loan> loansByDateOfExpiration();
}
