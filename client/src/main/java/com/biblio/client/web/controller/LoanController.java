package com.biblio.client.web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoanController {

    @GetMapping("/loans")
    public String userLoans(Model model) {


        return "loan";
    }
}
