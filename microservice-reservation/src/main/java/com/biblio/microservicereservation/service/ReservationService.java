package com.biblio.microservicereservation.service;


import com.biblio.microservicereservation.model.Reservation;

public interface ReservationService {

    Reservation getOne(Long id);

    void delete(Reservation reservation);

    Reservation saveNew(Reservation newReservation);

    boolean reservationExist(Reservation reservation);

    long countReservationsByDocumentId (Long documentId);

    boolean isReservationPossible (Long documentId);
}
