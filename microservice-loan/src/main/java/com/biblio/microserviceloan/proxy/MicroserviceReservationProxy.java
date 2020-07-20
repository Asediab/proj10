package com.biblio.microserviceloan.proxy;


import com.biblio.microserviceloan.DTO.ReservationDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "microservice-reservation", fallback = MicroserviceDocumentProxy.DocumentFallback.class, configuration = AccountClientConfiguration.class)
public interface MicroserviceReservationProxy {

    @GetMapping(value = "/reservation/sendMail/{documentId}")
     ResponseEntity<Void> sendMail(@PathVariable("documentId") Long documentId);

    @DeleteMapping(value = "/reservation/delete")
    public ResponseEntity<Void> deleteReservation(@RequestBody ReservationDTO reservation);


    class ReservationFallback implements MicroserviceReservationProxy {
        @Override
        public ResponseEntity<Void> sendMail(Long documentId) {
            return null;
        }

        @Override
        public ResponseEntity<Void> deleteReservation(ReservationDTO reservation) {
            return null;
        }
    }


}
