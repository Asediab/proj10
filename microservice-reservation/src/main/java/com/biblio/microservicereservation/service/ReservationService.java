package com.biblio.microservicereservation.service;


import com.biblio.microservicereservation.model.Reservation;

import java.util.List;

public interface ReservationService {

    Reservation getOne(Long id);

    Reservation getOneActive(Long id);

    void delete(Reservation reservation);

    Reservation saveNew(Reservation newReservation);

    boolean reservationExist(Reservation reservation);

    long countReservationsByDocumentId (Long documentId);

    boolean isReservationPossible (Long documentId);

    List<Reservation> getReservationsByUserId(Long userId);

    List<Reservation> getReservationsByDocumentId(Long documentId);
}
