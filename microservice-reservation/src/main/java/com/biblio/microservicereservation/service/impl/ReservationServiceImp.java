package com.biblio.microservicereservation.service.impl;

import com.biblio.microservicereservation.DTO.DocumentDTO;
import com.biblio.microservicereservation.dao.ReservationDAO;
import com.biblio.microservicereservation.model.Reservation;
import com.biblio.microservicereservation.proxy.MicroserviceDocumentProxy;
import com.biblio.microservicereservation.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ReservationServiceImp implements ReservationService {

    @Autowired
    private MicroserviceDocumentProxy documentProxy;

    @Autowired
    private ReservationDAO reservationDAO;

    @Override
    public Reservation getOne(Long id) {
        return reservationDAO.getOne(id);
    }

    @Override
    public void delete(Reservation reservation) {
        if (reservationDAO.existsByActiveIsTrueAndDocumentIdAndUserId(reservation.getDocumentId(), reservation.getUserId())) {
            documentProxy.deteleReservation(reservation.getDocumentId());
            reservation.setActive(Boolean.FALSE);
            reservationDAO.save(reservation);
        }
    }

    @Override
    public Reservation saveNew(Reservation newReservation) {
        if (!reservationDAO.existsByActiveIsTrueAndDocumentIdAndUserId(newReservation.getDocumentId(), newReservation.getUserId())) {
            documentProxy.addReservation(newReservation.getDocumentId());
            newReservation.setActive(Boolean.TRUE);
            newReservation.setMailSent(Boolean.FALSE);
            newReservation.setTakenByUser(Boolean.FALSE);
            newReservation.setDateCreation(LocalDate.now());
            return reservationDAO.save(newReservation);
        }
        return null;
    }

    @Override
    public boolean reservationExist(Reservation reservation) {
        return reservationDAO.existsByActiveIsTrueAndDocumentIdAndUserId(reservation.getDocumentId(), reservation.getUserId());
    }

    @Override
    public long countReservationsByDocumentId(Long documentId) {
        return reservationDAO.countReservationByDocumentIdAndActiveIsTrue(documentId);
    }

    @Override
    public boolean isReservationPossible(Long documentId) {
        long reservationCount = countReservationsByDocumentId(documentId);
        DocumentDTO doc = documentProxy.getDocumentByID(documentId);
        int resMax = doc.getCopyAvailable() * 2;
        if(reservationCount < resMax){
            return true;
        }
        return false;
    }
}
