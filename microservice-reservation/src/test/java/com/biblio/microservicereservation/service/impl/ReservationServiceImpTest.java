package com.biblio.microservicereservation.service.impl;

import com.biblio.microservicereservation.DTO.DocumentDTO;
import com.biblio.microservicereservation.dao.ReservationDAO;
import com.biblio.microservicereservation.model.Reservation;
import com.biblio.microservicereservation.proxy.MicroserviceBatchProxy;
import com.biblio.microservicereservation.proxy.MicroserviceDocumentProxy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class ReservationServiceImpTest {
    @Mock
    MicroserviceDocumentProxy documentProxy;
    @Mock
    MicroserviceBatchProxy mailProxy;
    @Mock
    ReservationDAO reservationDAO;
    @InjectMocks
    ReservationServiceImp reservationServiceImp;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetOne() {
        when(reservationDAO.getOne(anyLong())).thenReturn(new Reservation());

        Reservation result = reservationServiceImp.getOne(Long.valueOf(1));
        Assertions.assertEquals(new Reservation(), result);
    }

    @Test
    void testDelete() {
        when(documentProxy.deleteReservation(anyLong())).thenReturn(null);
        when(reservationDAO.findByDocumentIdAndUserIdAndIsActiveTrue(anyLong(), anyLong())).thenReturn(new Reservation());
        when(reservationDAO.existsByIsActiveTrueAndDocumentIdAndUserId(anyLong(), anyLong())).thenReturn(true);

        reservationServiceImp.delete(new Reservation());
    }

    @Test
    void testSaveNew() {
        when(documentProxy.addReservation(anyLong())).thenReturn(null);
        when(reservationDAO.existsByIsActiveTrueAndDocumentIdAndUserId(anyLong(), anyLong())).thenReturn(true);

        Reservation result = reservationServiceImp.saveNew(new Reservation());
        Assertions.assertEquals(new Reservation(), result);
    }

    @Test
    void testReservationExist() {
        when(reservationDAO.existsByIsActiveTrueAndDocumentIdAndUserId(anyLong(), anyLong())).thenReturn(true);

        boolean result = reservationServiceImp.reservationExist(new Reservation());
        Assertions.assertEquals(true, result);
    }

    @Test
    void testCountReservationsByDocumentId() {
        when(reservationDAO.countReservationByDocumentIdAndIsActiveTrue(anyLong())).thenReturn(Long.valueOf(1));

        long result = reservationServiceImp.countReservationsByDocumentId(Long.valueOf(1));
        Assertions.assertEquals(0L, result);
    }

    @Test
    void testIsReservationPossible() {
        when(documentProxy.getDocumentByID(anyLong())).thenReturn(new DocumentDTO());
        when(reservationDAO.countReservationByDocumentIdAndIsActiveTrue(anyLong())).thenReturn(Long.valueOf(1));

        boolean result = reservationServiceImp.isReservationPossible(Long.valueOf(1));
        Assertions.assertEquals(true, result);
    }

    @Test
    void testGetReservationsByUserId() {
        when(reservationDAO.findByUserIdAndIsActiveTrue(anyLong())).thenReturn(Arrays.<Reservation>asList(new Reservation()));

        List<Reservation> result = reservationServiceImp.getReservationsByUserId(Long.valueOf(1));
        Assertions.assertEquals(Arrays.<Reservation>asList(new Reservation()), result);
    }

    @Test
    void testGetReservationsByDocumentId() {
        when(reservationDAO.findByDocumentIdAndIsActiveTrueOrderByDateCreationDesc(anyLong())).thenReturn(Arrays.<Reservation>asList(new Reservation()));

        List<Reservation> result = reservationServiceImp.getReservationsByDocumentId(Long.valueOf(1));
        Assertions.assertEquals(Arrays.<Reservation>asList(new Reservation()), result);
    }

    @Test
    void testGetOneActive() {
        when(reservationDAO.findByIdAndIsActiveTrue(anyLong())).thenReturn(new Reservation());

        Reservation result = reservationServiceImp.getOneActive(Long.valueOf(1));
        Assertions.assertEquals(new Reservation(), result);
    }

    @Test
    void testMailSender() {
        when(documentProxy.deleteReservation(anyLong())).thenReturn(null);
        when(mailProxy.sendMAil(any())).thenReturn(null);
        when(reservationDAO.findByIsActiveTrueAndDocumentIdOrderByDateCreationAsc(anyLong())).thenReturn(Arrays.<Reservation>asList(new Reservation()));

        reservationServiceImp.mailSender(Long.valueOf(1));
    }

    @Test
    void testGetReservationsForMails() {
        when(documentProxy.deleteReservation(anyLong())).thenReturn(null);
        when(reservationDAO.findByIsActiveTrueAndIsMailSentTrue()).thenReturn(Arrays.<Reservation>asList(new Reservation()));
        when(reservationDAO.findNextReservationByDocumentId(anyLong())).thenReturn(new Reservation());

        List<Reservation> result = reservationServiceImp.getReservationsForMails();
        Assertions.assertEquals(Arrays.<Reservation>asList(new Reservation()), result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme