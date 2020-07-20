package com.biblio.microservicereservation.service.impl;

import com.biblio.microservicereservation.DTO.DocumentDTO;
import com.biblio.microservicereservation.DTO.ReservationDTO;
import com.biblio.microservicereservation.dao.ReservationDAO;
import com.biblio.microservicereservation.model.Reservation;
import com.biblio.microservicereservation.proxy.MicroserviceDocumentProxy;
import com.biblio.microservicereservation.proxy.MicroserviceMailProxy;
import com.biblio.microservicereservation.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationServiceImp implements ReservationService {

    @Autowired
    private MicroserviceDocumentProxy documentProxy;

    @Autowired
    private MicroserviceMailProxy mailProxy;

    @Autowired
    private ReservationDAO reservationDAO;

    @Override
    public Reservation getOne(Long id) {
        return reservationDAO.getOne(id);
    }

    @Override
    public void delete(Reservation reservation) {
        if (reservationDAO.existsByIsActiveTrueAndDocumentIdAndUserId(reservation.getDocumentId(), reservation.getUserId())) {
            Reservation reservation1 = reservationDAO.findByDocumentIdAndUserIdAndIsActiveTrue(reservation.getDocumentId(), reservation.getUserId());
            documentProxy.deleteReservation(reservation1.getDocumentId());
            reservation1.setActive(Boolean.FALSE);
            reservationDAO.save(reservation1);
        }
    }

    @Override
    public Reservation saveNew(Reservation newReservation) {
        if (!reservationDAO.existsByIsActiveTrueAndDocumentIdAndUserId(newReservation.getDocumentId(), newReservation.getUserId())) {

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
        return reservationDAO.existsByIsActiveTrueAndDocumentIdAndUserId(reservation.getDocumentId(), reservation.getUserId());
    }

    @Override
    public long countReservationsByDocumentId(Long documentId) {
        return reservationDAO.countReservationByDocumentIdAndIsActiveTrue(documentId);
    }

    @Override
    public boolean isReservationPossible(Long documentId) {
        long reservationCount = countReservationsByDocumentId(documentId);
        DocumentDTO doc = documentProxy.getDocumentByID(documentId);
        int resMax = doc.getCopyTotal() * 2;
        if (reservationCount < resMax) {
            return true;
        }
        return false;
    }

    @Override
    public List<Reservation> getReservationsByUserId(Long userId) {
        return reservationDAO.findByUserIdAndIsActiveTrue(userId);
    }

    @Override
    public List<Reservation> getReservationsByDocumentId(Long documentId) {
        return reservationDAO.findByDocumentIdAndIsActiveTrueOrderByDateCreationDesc(documentId);
    }

    @Override
    public Reservation getOneActive(Long id) {
        return reservationDAO.findByIdAndIsActiveTrue(id);
    }

    @Override
    public void mailSender(Long documentId) {
        List<Reservation> reservationList = this.checkReservationMailExpirationDate(documentId);
        if (!reservationList.isEmpty()){
            for (int i = 0; i <= reservationList.size() - 1 ; i++) {
                if (reservationList.get(i).isActive() && !reservationList.get(i).isMailSent()){
                    Reservation reservation = reservationList.get(i);
                    reservation.setMailSent(Boolean.TRUE);
                    reservation.setMailSentDate(LocalDate.now());
                    reservation.setMailExpirationDate(reservation.getMailSentDate().plusDays(2));
                    ReservationDTO reservationDTO = new ReservationDTO();
                    reservationDTO.setDocumentId(reservation.getDocumentId());
                    reservationDTO.setUserName(reservation.getUserName());
                    reservationDTO.setUserSurname(reservation.getUserSurname());
                    reservationDTO.setUserEmail(reservation.getUserEmail());
                    reservationDTO.setDocumentName(reservation.getDocumentName());
                    mailProxy.sendMAil(reservationDTO);
                    reservationDAO.save(reservation);
                    break;
                }
            }
        }
    }

    private List<Reservation> checkReservationMailExpirationDate(Long documentId) {
        List<Reservation> reservationList = reservationDAO.findByIsActiveTrueAndDocumentIdOrderByDateCreationAsc(documentId);
        if (!reservationList.isEmpty()){
            for (int i = 0; i <= reservationList.size() - 1 ; i++) {
                if (reservationList.get(i).isMailSent() && reservationList.get(i).getMailExpirationDate().isBefore(LocalDate.now())) {
                    reservationList.get(i).setActive(Boolean.FALSE);
                    reservationDAO.save(reservationList.get(i));
                    documentProxy.deleteReservation(reservationList.get(i).getDocumentId());
                }
            }
        }
        return reservationList;
    }
}
