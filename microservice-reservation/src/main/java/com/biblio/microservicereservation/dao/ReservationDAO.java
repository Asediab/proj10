package com.biblio.microservicereservation.dao;



import com.biblio.microservicereservation.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationDAO extends JpaRepository<Reservation, Long> {

    Reservation getOne(Long id);

    Long countReservationByDocumentIdAndActiveIsTrue(Long id);

    List<Reservation> findByUserIdAndActiveIsTrue(Long id);

    List<Reservation> findAllByActiveIsTrueOrderByDateCreationAsc();

    boolean existsByActiveIsTrueAndDocumentIdAndUserId(Long documentId, Long userId);

}
