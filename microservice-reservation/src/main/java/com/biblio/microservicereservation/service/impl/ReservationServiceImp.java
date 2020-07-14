package com.biblio.microservicereservation.service.impl;

import com.biblio.microservicereservation.dao.ReservationDAO;
import com.biblio.microservicereservation.model.Reservation;
import com.biblio.microservicereservation.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ReservationServiceImp implements ReservationService {

    @Autowired
    private ReservationDAO reservationDAO;

    @Override
    public Reservation getOne(Long id) {
        return reservationDAO.getOne(id);
    }

    @Override
    public void delete(Reservation reservation) {
//TODO  отправить запрос на уменьшение количества резерваций в микросервис документы

        reservation.setActive(Boolean.FALSE);
        reservationDAO.save(reservation);
    }

    @Override
    public Reservation saveNew(Reservation newReservation) {
        //TODO  отправить запрос на увеличение количества резерваций в микросервис документы
        if (!reservationDAO.existsByActiveIsTrueAndDocumentIdAndUserId(newReservation.getDocumentId(), newReservation.getUserId())) {
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
}
