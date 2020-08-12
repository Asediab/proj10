package com.biblio.microservicereservation.dao;


import com.biblio.microservicereservation.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReservationDAO extends JpaRepository<Reservation, Long> {

    Reservation getOne(Long id);

    Long countReservationByDocumentIdAndIsActiveTrue(Long documentId);

    Reservation findByIdAndIsActiveTrue(Long id);

    List<Reservation> findByUserIdAndIsActiveTrue(Long userId);

    List<Reservation> findByDocumentIdAndIsActiveTrueOrderByDateCreationAsc(Long documentId);

    Reservation findByDocumentIdAndUserIdAndIsActiveTrue(Long documentId, Long userId);

    boolean existsByIsActiveTrueAndDocumentIdAndUserId(Long documentId, Long userId);

    List<Reservation> findByIsActiveTrueAndDocumentIdOrderByDateCreationAsc(Long documentId);

    List<Reservation> findByIsActiveTrueAndIsMailSentTrue();

    @Query(value = "SELECT * FROM reservation WHERE document_id = :documentId AND is_mail_sent = FALSE ORDER BY date_creation LIMIT 1", nativeQuery = true)
    Reservation findNextReservationByDocumentId(Long documentId);

}
