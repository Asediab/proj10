package com.biblio.microservicebatch.proxy;

import com.biblio.microservicebatch.model.ReservationDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collections;
import java.util.List;

@FeignClient(name = "microservice-reservation", fallback = MicroserviceReservationProxy.ReservationFallback.class)
public interface MicroserviceReservationProxy {

    @GetMapping(value = "/reservation/sendMails")
    List<ReservationDTO> sendListMail();

    @Component
    class ReservationFallback implements MicroserviceReservationProxy {
        @Override
        public List<ReservationDTO> sendListMail() {
            return Collections.emptyList();
        }
    }
}
