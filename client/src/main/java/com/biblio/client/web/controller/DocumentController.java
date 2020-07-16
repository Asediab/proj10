package com.biblio.client.web.controller;

import com.biblio.client.DTO.DocumentDTO;
import com.biblio.client.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DocumentController {


    @Autowired
    private DocumentService documentService;


    @GetMapping("/")
    public String documents(@RequestParam(name = "author", required = false, defaultValue = "") String author,
                            @RequestParam(name = "title", required = false, defaultValue = "") String title,
                            OAuth2Authentication principal,
                            Model model,
                            @PageableDefault(size = 10) Pageable pageable) {

        Page<DocumentDTO> page = documentService.getDocuments(pageable, author, title, principal);
        model.addAttribute("documents", page);
        model.addAttribute("url", "/?author=" + author + "&title=" + title);
        model.addAttribute("numberOfPages", page.getTotalPages());
        return "documents";
    }
}
