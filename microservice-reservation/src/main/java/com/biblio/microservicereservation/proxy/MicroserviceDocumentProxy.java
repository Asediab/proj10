package com.biblio.microservicereservation.proxy;

import com.biblio.microservicereservation.DTO.DocumentDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@FeignClient(name = "microservice-document", fallback = MicroserviceDocumentProxy.DocumentFallback.class, configuration = AccountClientConfiguration.class)
public interface MicroserviceDocumentProxy {

    @GetMapping(value = "/documents/API/{idDoc}")
    DocumentDTO getDocumentByID(@PathVariable("idDoc") Long docID);

    @PutMapping(value = "/documents/API/addReservation/{idDoc}")
    ResponseEntity<Void> addReservation(@PathVariable("idDoc") Long docID);

    @DeleteMapping(value = "/documents/API/deleteReservation/{idDoc}")
    ResponseEntity<Void> deteleReservation(@PathVariable("idDoc") Long docID);




    class DocumentFallback implements MicroserviceDocumentProxy {

        @Override
        public DocumentDTO getDocumentByID(Long docCopyID) {
            return new DocumentDTO();
        }

        @Override
        public ResponseEntity<Void> addReservation(Long docID) {
            return null;
        }

        @Override
        public ResponseEntity<Void> deteleReservation(Long docID) {
            return null;
        }
    }
}
