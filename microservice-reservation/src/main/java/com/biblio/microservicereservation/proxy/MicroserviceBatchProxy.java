package com.biblio.microservicereservation.proxy;

import com.biblio.microservicereservation.DTO.ReservationDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "microservice-batch", fallback = MicroserviceBatchProxy.MailFallback.class, configuration = AccountClientConfiguration.class)
public interface MicroserviceBatchProxy {

    @PostMapping(value = "/mail/send")
    ResponseEntity<Void> sendMAil(@RequestBody ReservationDTO reservationDTO);



    class MailFallback implements MicroserviceBatchProxy {
        @Override
        public ResponseEntity<Void> sendMAil(ReservationDTO reservationDTO) {
            return null;
        }
    }
}
