package com.biblio.client.service.impl;

import com.biblio.client.DTO.LoanDTO;
import com.biblio.client.DTO.ReservationDTO;
import com.biblio.client.proxy.MicroserviceReservationProxy;
import com.biblio.client.service.LoanService;
import com.biblio.client.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private MicroserviceReservationProxy reservationProxy;

    @Autowired
    private LoanService loanService;

    @Override
    public Page<ReservationDTO> reservationByUser(Pageable pageable, Long userId) {
        List<ReservationDTO> reservationDTOList = reservationProxy.getByUserId(userId);

        for (ReservationDTO reservation : reservationDTOList) {
            LoanDTO loanDTO = loanService.dateExpirationLoanByDocumentId(reservation.getDocumentId());
            reservation.setDateReturn(loanDTO.getDateExpiration());
        }

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), reservationDTOList.size());
        Page<ReservationDTO> pages = new PageImpl<ReservationDTO>(reservationDTOList.subList(start, end), pageable, reservationDTOList.size());

        return pages;
    }

    @Override
    public void addReservation(ReservationDTO reservation) {
        reservationProxy.addReservation(reservation);
    }

    @Override
    public void deleteReservation(Long reservationId, OAuth2Authentication principal, String documentName, Long documentId) {
        LinkedHashMap map = (LinkedHashMap) principal.getUserAuthentication().getDetails();
        map = (LinkedHashMap) map.get("principal");
        long userId = (int) map.get("id");
        String userName = (String) map.get("name");
        String userSurname = (String) map.get("surname");
        ReservationDTO newReservation = new ReservationDTO();
        newReservation.setId(reservationId);
        newReservation.setUserId(userId);
        newReservation.setUserName(userName);
        newReservation.setUserSurname(userSurname);
        newReservation.setDocumentName(documentName);
        newReservation.setDocumentId(documentId);
        reservationProxy.deleteReservation(newReservation);
    }
}
