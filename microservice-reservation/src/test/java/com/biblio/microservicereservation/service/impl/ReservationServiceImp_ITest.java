package com.biblio.microservicereservation.service.impl;

import com.biblio.microservicereservation.dao.ReservationDAO;
import com.biblio.microservicereservation.model.Reservation;
import com.biblio.microservicereservation.proxy.MicroserviceBatchProxy;
import com.biblio.microservicereservation.proxy.MicroserviceDocumentProxy;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;


@SpringBootTest
public class ReservationServiceImp_ITest {

    @MockBean
    MicroserviceDocumentProxy documentProxy;
    @MockBean
    MicroserviceBatchProxy mailProxy;

    @Autowired
    ReservationDAO reservationDAO;

    @Autowired
    ReservationServiceImp reservationServiceImp = new ReservationServiceImp(documentProxy, mailProxy, reservationDAO);

    static Reservation res1 = new Reservation();
    static Reservation res3;


    @BeforeAll
    static void setUp() {
        res1.setDocumentId(100L);
        res1.setUserId(100L);
        res1.setUserName("Name");
        res1.setUserSurname("Surname");
        res1.setUserEmail("Email");
        res1.setDocumentName("Doc name");
    }

    @Test
    @Tag("testSaveNew and Delete")
    @DisplayName("Test saveNew and Delete")
    void saveNew() {
        given(this.documentProxy.addReservation(anyLong())).willReturn(null);
        res3 = reservationServiceImp.saveNew(res1);
        assertTrue(reservationServiceImp.reservationExist(res3));

        given(this.documentProxy.deleteReservation(anyLong())).willReturn(null);
        reservationServiceImp.delete(res3);
        assertFalse(reservationServiceImp.reservationExist(res3));
    }
}
