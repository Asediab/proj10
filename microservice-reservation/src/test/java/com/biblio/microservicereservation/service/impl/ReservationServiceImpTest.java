package com.biblio.microservicereservation.service.impl;

import com.biblio.microservicereservation.DTO.DocumentDTO;
import com.biblio.microservicereservation.DTO.ReservationDTO;
import com.biblio.microservicereservation.dao.ReservationDAO;
import com.biblio.microservicereservation.model.Reservation;
import com.biblio.microservicereservation.proxy.MicroserviceBatchProxy;
import com.biblio.microservicereservation.proxy.MicroserviceDocumentProxy;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

class ReservationServiceImpTest {
    @Mock
    MicroserviceDocumentProxy documentProxy = mock(MicroserviceDocumentProxy.class);
    @Mock
    MicroserviceBatchProxy mailProxy = mock(MicroserviceBatchProxy.class);
    @Mock
    ReservationDAO reservationDAO = mock(ReservationDAO.class);
    @InjectMocks
    ReservationServiceImp reservationServiceImp = new ReservationServiceImp(documentProxy, mailProxy, reservationDAO);

    static Reservation res1 = new Reservation();
    static Reservation res2 = new Reservation();

    @BeforeEach
    void setUp() {
        res1.setId(1L);
        res1.setDocumentId(1L);
        res1.setUserId(1L);
        res1.setUserName("Name");
        res1.setUserSurname("Surname");
        res1.setUserEmail("Email");
        res1.setDocumentName("Doc name");

        res2.setId(2L);
        res2.setDocumentId(2L);
        res2.setUserId(2L);
        res2.setUserName("Name2");
        res2.setUserSurname("Surname2");
        res2.setUserEmail("Email2");
        res2.setDocumentName("Doc name2");
    }

    @Test
    @Tag("testGetOne")
    @DisplayName("Test GetOne return Reservation")
    void testGetOne() {
        when(reservationDAO.getOne(anyLong())).thenReturn(res1);

        Reservation result = reservationServiceImp.getOne(1L);
        Assertions.assertEquals(res1, result);
    }


    @Test
    @Tag("testSaveNew")
    @DisplayName("Test SaveNew return Reservation")
    void testSaveNew() {
        Reservation redd = new Reservation();
        redd.setId(1L);
        redd.setDocumentId(1L);
        redd.setUserId(1L);
        redd.setUserName("Name");
        redd.setUserSurname("Surname");
        redd.setUserEmail("Email");
        redd.setDocumentName("Doc name");
        redd.setActive(Boolean.TRUE);
        redd.setMailSent(Boolean.FALSE);
        redd.setTakenByUser(Boolean.FALSE);
        redd.setDateCreation(LocalDate.now());
        when(documentProxy.addReservation(anyLong())).thenReturn(null);
        when(reservationDAO.existsByIsActiveTrueAndDocumentIdAndUserId(anyLong(), anyLong())).thenReturn(Boolean.FALSE);
        when(reservationDAO.save(any(Reservation.class))).thenReturn(redd);

        Assertions.assertEquals(redd, reservationServiceImp.saveNew(res1));
        Assertions.assertNotNull(reservationServiceImp.saveNew(res1));
        Assertions.assertNotEquals(res1, reservationServiceImp.saveNew(res1));

    }

    @Test
    @Tag("testReservationExist")
    @DisplayName("Test ReservationExist return true if existe")
    void testReservationExist() {
        when(reservationDAO.existsByIsActiveTrueAndDocumentIdAndUserId(anyLong(), anyLong())).thenReturn(true);

        boolean result = reservationServiceImp.reservationExist(res1);
        Assertions.assertTrue(result);
    }

    @Test
    @Tag("testReservationExist")
    @DisplayName("Test ReservationExist return false if not existe")
    void testReservationExist_returnFalse() {
        when(reservationDAO.existsByIsActiveTrueAndDocumentIdAndUserId(anyLong(), anyLong())).thenReturn(false);

        boolean result = reservationServiceImp.reservationExist(new Reservation());
        Assertions.assertFalse(result);
    }

    @Test
    @Tag("testCountReservationsByDocumentId")
    @DisplayName("Test CountReservationsByDocumentId return number of Reservations")
    void testCountReservationsByDocumentId() {
        when(reservationDAO.countReservationByDocumentIdAndIsActiveTrue(anyLong())).thenReturn(Long.valueOf(1));

        long result = reservationServiceImp.countReservationsByDocumentId(1L);
        Assertions.assertEquals(1, result);
    }

    @Test
    @Tag("testIsReservationPossible")
    @DisplayName("Test IsReservationPossible return true if number of Reservation < TotalCopy*2")
    void testIsReservationPossible() {
        DocumentDTO doc = new DocumentDTO();
        doc.setCopyTotal(10);
        when(documentProxy.getDocumentByID(anyLong())).thenReturn(doc);
        when(reservationDAO.countReservationByDocumentIdAndIsActiveTrue(anyLong())).thenReturn(Long.valueOf(10));

        boolean result = reservationServiceImp.isReservationPossible(1L);
        Assertions.assertTrue(result);
    }

    @Test
    @Tag("testIsReservationPossible")
    @DisplayName("Test IsReservationPossible return true if number of Reservation < TotalCopy*2")
    void testIsReservationPossible_returnFalse() {
        DocumentDTO doc = new DocumentDTO();
        doc.setCopyTotal(10);
        when(documentProxy.getDocumentByID(anyLong())).thenReturn(doc);
        when(reservationDAO.countReservationByDocumentIdAndIsActiveTrue(anyLong())).thenReturn(Long.valueOf(50));

        boolean result = reservationServiceImp.isReservationPossible(1L);
        Assertions.assertFalse(result);
    }

    @Test
    @Tag("testGetReservationsByUserId")
    @DisplayName("Test GetReservationsByUserId return List<Reservation>")
    void testGetReservationsByUserId() {
        when(reservationDAO.findByUserIdAndIsActiveTrue(anyLong())).thenReturn(Arrays.asList(res1, res2));

        List<Reservation> result = reservationServiceImp.getReservationsByUserId(1L);
        Assertions.assertEquals(Arrays.asList(res1, res2), result);
        Assertions.assertNotNull(result);
    }

    @Test
    @Tag("testGetReservationsByDocumentId")
    @DisplayName("Test GetReservationsByDocumentId")
    void testGetReservationsByDocumentId() {
        when(reservationDAO.findByDocumentIdAndIsActiveTrueOrderByDateCreationDesc(anyLong())).thenReturn(Collections.singletonList(res1));

        List<Reservation> result = reservationServiceImp.getReservationsByDocumentId(1L);
        Assertions.assertEquals(Collections.singletonList(res1), result);
    }

    @Test
    @Tag("testGetOneActive")
    @DisplayName("Test GetOneActive return Reservation")
    void testGetOneActive() {
        when(reservationDAO.findByIdAndIsActiveTrue(anyLong())).thenReturn(res1);

        Reservation result = reservationServiceImp.getOneActive(1L);
        Assertions.assertEquals(res1, result);
    }

    @Test
    @Tag("testMailSender")
    @DisplayName("Test MailSender take first reservation by DateCreation and send Mail")
    void testMailSender() {
        res1.setActive(Boolean.TRUE);
        res2.setActive(Boolean.TRUE);
        res2.setDateCreation(LocalDate.of(2020, 12, 20));
        when(documentProxy.deleteReservation(anyLong())).thenReturn(null);
        when(mailProxy.sendMAil(any())).thenReturn(null);
        when(reservationDAO.findByIsActiveTrueAndDocumentIdOrderByDateCreationAsc(anyLong())).thenReturn(Arrays.asList(res1, res2));
        when(reservationDAO.save(any(Reservation.class))).thenReturn(res1);

        reservationServiceImp.mailSender(1L);
        verify(reservationDAO, atMost(1)).save(res1);
    }

    @Test
    @Tag("testGetReservationsForMails")
    @DisplayName("Test GetReservationsForMails return List<Reservation> for send mails")
    void testGetReservationsForMails() {
        res2.setMailExpirationDate(LocalDate.of(1999, 10, 2));
        res1.setMailExpirationDate(LocalDate.now());
        when(documentProxy.deleteReservation(anyLong())).thenReturn(null);
        when(reservationDAO.findByIsActiveTrueAndIsMailSentTrue()).thenReturn(Arrays.asList(res1, res2));
        when(reservationDAO.findNextReservationByDocumentId(anyLong())).thenReturn(res1);

        List<Reservation> result = reservationServiceImp.getReservationsForMails();
        Assertions.assertEquals(Collections.singletonList(res1), result);
    }
}

