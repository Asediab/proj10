package com.biblio.microservicereservation.service.impl;

import com.biblio.microservicereservation.DTO.DocumentDTO;
import com.biblio.microservicereservation.DTO.ReservationDTO;
import com.biblio.microservicereservation.dao.ReservationDAO;
import com.biblio.microservicereservation.model.Reservation;
import com.biblio.microservicereservation.proxy.MicroserviceDocumentProxy;
import com.biblio.microservicereservation.proxy.MicroserviceBatchProxy;
import com.biblio.microservicereservation.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ReservationServiceImp implements ReservationService {

    @Autowired
    private MicroserviceDocumentProxy documentProxy;

    @Autowired
    private MicroserviceBatchProxy mailProxy;

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
                    reservationList.get(i).setMailSent(Boolean.TRUE);
                    reservationList.get(i).setMailSentDate(LocalDate.now());
                    reservationList.get(i).setMailExpirationDate(reservationList.get(i).getMailSentDate().plusDays(2));
                    ReservationDTO reservationDTO = new ReservationDTO();
                    reservationDTO.setDocumentId(reservationList.get(i).getDocumentId());
                    reservationDTO.setUserName(reservationList.get(i).getUserName());
                    reservationDTO.setUserSurname(reservationList.get(i).getUserSurname());
                    reservationDTO.setUserEmail(reservationList.get(i).getUserEmail());
                    reservationDTO.setDocumentName(reservationList.get(i).getDocumentName());
                    mailProxy.sendMAil(reservationDTO);
                    reservationDAO.save(reservationList.get(i));
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

    @Override
    public List<Reservation> getReservationsForMails() {
        List<Reservation> reservationList = this.setNotActiveByMailExpirationDate();
        List<Reservation> listForMail = new ArrayList<>();
        if (reservationList.isEmpty()){
            return null;
        } else {
            for (Reservation reservation : reservationList) {
                Reservation res = reservationDAO.findNextReservationByDocumentId(reservation.getDocumentId());
                if (res != null){
                    res.setMailSent(Boolean.TRUE);
                    res.setMailSentDate(LocalDate.now());
                    res.setMailExpirationDate(res.getMailSentDate().plusDays(2));
                    reservationDAO.save(res);
                    listForMail.add(res);
                }
            }
            return listForMail;
        }
    }

    private List<Reservation> setNotActiveByMailExpirationDate() {
        List<Reservation> listNotActive = new ArrayList<>();
        List<Reservation> listByDAO = reservationDAO.findByIsActiveTrueAndIsMailSentTrue();
        if (listByDAO.isEmpty()){
            return Collections.emptyList();
        } else {
            for (Reservation res : listByDAO) {
                if (res.getMailExpirationDate().isBefore(LocalDate.now())) {
                    res.setActive(Boolean.FALSE);
                    reservationDAO.save(res);
                    documentProxy.deleteReservation(res.getDocumentId());
                    listNotActive.add(res);
                }
            }
            return listNotActive;
        }
    }


}
