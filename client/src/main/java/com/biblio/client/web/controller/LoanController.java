package com.biblio.client.web.controller;


import com.biblio.client.DTO.LoanDTO;
import com.biblio.client.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class LoanController {
    @Autowired
    private LoanService loanService;

    @GetMapping("/loans")
    public String userLoans(Model model, Principal principal,
                            @PageableDefault(size = 10) Pageable pageable) {
        Page<LoanDTO> page = loanService.loansByUser(pageable, 3L);
        model.addAttribute("listLoans", page);

        return "loans";
    }

    @PostMapping("/loans")
    public String prolongLoan(@RequestParam("loanId") Long loan,
                              Model model) {

        loanService.prolongateLoan(loan);

        return "redirect:/loans";
    }

}
