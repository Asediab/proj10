package com.biblio.microservicereservation.dao;


import com.biblio.microservicereservation.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationDAO extends JpaRepository<Reservation, Long> {

    Reservation getOne(Long id);

    Long countReservationByDocumentIdAndIsActiveTrue(Long documentId);

    Reservation findByIdAndIsActiveTrue(Long id);

    List<Reservation> findByUserIdAndIsActiveTrue(Long userId);

    List<Reservation> findByDocumentIdAndIsActiveTrueOrderByDateCreationDesc(Long documentId);

    Reservation findByDocumentIdAndUserIdAndIsActiveTrue(Long documentId, Long userId);

    boolean existsByIsActiveTrueAndDocumentIdAndUserId(Long documentId, Long userId);

    List<Reservation> findByIsActiveTrueAndDocumentIdOrderByDateCreationAsc(Long documentId);

}
