package com.biblio.client.proxy;

import com.biblio.client.DTO.ReservationDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@FeignClient(name = "microservice-reservation", fallback = MicroserviceReservationProxy.ReservationFallback.class)
public interface MicroserviceReservationProxy {

    @PostMapping(value = "/reservation/add")
    ResponseEntity<Void> addReservation(@RequestBody ReservationDTO reservation);

    @DeleteMapping(value = "/reservation/delete")
    ResponseEntity<Void> deleteReservation(@RequestBody ReservationDTO reservation);

    @GetMapping(value = "/reservation/byUser/{userId}")
    List<ReservationDTO> getByUserId(@PathVariable("userId") Long userId);

    @GetMapping(value = "/reservation/byDocument/{documentId}")
    List<ReservationDTO> getByDocumentId(@PathVariable("documentId") Long documentId);


    @Component
    class ReservationFallback implements MicroserviceReservationProxy {

        @Override
        public ResponseEntity<Void> addReservation(ReservationDTO reservation) {
            return null;
        }

        @Override
        public ResponseEntity<Void> deleteReservation(ReservationDTO reservation) {
            return null;
        }

        @Override
        public List<ReservationDTO> getByUserId(Long userId) {
            return Collections.emptyList();
        }

        @Override
        public List<ReservationDTO> getByDocumentId(Long documentId) {
            return Collections.emptyList();
        }
    }
}
