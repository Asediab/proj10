package com.biblio.client.web.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class DocumentController {

    @GetMapping("/")
    public String index(Model model) {
        return "documents";
    }
}
