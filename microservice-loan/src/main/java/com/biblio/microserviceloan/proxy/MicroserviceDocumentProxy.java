package com.biblio.microserviceloan.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;


@FeignClient(name = "microservice-document", fallback = MicroserviceDocumentProxy.DocumentFallback.class, configuration = AccountClientConfiguration.class)
public interface MicroserviceDocumentProxy {

    @PutMapping(value = "/documents/API/addCopyAvailable/{idDoc}")
    ResponseEntity<Void> addCopyAvailable(@PathVariable("idDoc") Long docID);

    @DeleteMapping(value = "/documents/API/deleteCopyAvailable/{idDoc}")
    ResponseEntity<Void> deleteCopyAvailable(@PathVariable("idDoc") Long docID);


    class DocumentFallback implements MicroserviceDocumentProxy {

        @Override
        public ResponseEntity<Void> addCopyAvailable(Long docID) {
            return null;
        }

        @Override
        public ResponseEntity<Void> deleteCopyAvailable(Long docID) {
            return null;
        }
    }
}
