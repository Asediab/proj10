package com.biblio.client.proxy;

import com.biblio.client.DTO.DocumentDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

//@FeignClient(name = "zuul-server")
//@RibbonClient(name = "microservice-document")


@FeignClient(name = "microservice-document")
public interface MicroserviceDocumentProxy {

    @GetMapping(value = "/microservice-document/documents/")
    List<DocumentDTO> listDocuments();

    @GetMapping(value = "/microservice-document/documents/search")
    List<DocumentDTO> searchDocuments(@RequestParam(name = "titre", value = "", required = true) String titre,
                                      @RequestParam(name = "author", value = "", required = true) String author);
}