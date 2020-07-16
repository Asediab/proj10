package com.biblio.client.service;


import com.biblio.client.DTO.ReservationDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import java.util.List;

public interface ReservationService {

    Page<ReservationDTO> reservationByUser(Pageable pageable, Long userId);

    void addReservation(Long documentId, String documentName, OAuth2Authentication principal);

    void deleteReservation(Long reservationId, OAuth2Authentication principal, String documentName, Long documentId);
}
