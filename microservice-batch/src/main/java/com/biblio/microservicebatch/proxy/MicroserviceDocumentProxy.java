package com.biblio.microservicebatch.proxy;

import com.biblio.microservicebatch.model.CopyOfDocument;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "microservice-document")
public interface MicroserviceDocumentProxy {

    @GetMapping(value = "/documents/{idCopyDoc}")
    CopyOfDocument getDocumentByID(@PathVariable("idCopyDoc") Long docCopyID);

}
