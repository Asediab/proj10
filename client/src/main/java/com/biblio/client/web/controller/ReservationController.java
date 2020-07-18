package com.biblio.client.web.controller;

import com.biblio.client.DTO.LoanDTO;
import com.biblio.client.DTO.ReservationDTO;
import com.biblio.client.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.LinkedHashMap;

@Controller
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/reservations")
    public String userReservations(Model model, OAuth2Authentication principal,
                                   @PageableDefault(size = 10) Pageable pageable) {

        LinkedHashMap map = (LinkedHashMap) principal.getUserAuthentication().getDetails();
        map = (LinkedHashMap) map.get("principal");
        long userId = (int) map.get("id");
        Page<ReservationDTO> page = reservationService.reservationByUser(pageable, userId);
        model.addAttribute("listReservations", page);
        model.addAttribute("url", "/");
        model.addAttribute("numberOfPages", page.getTotalPages());

        return "reservations";
    }

    @PostMapping("/reservations")
    public String deleteReservation(@RequestParam("reservationId") Long reservation,
                                    @RequestParam("documentName") String documentName,
                                    @RequestParam("documentId") Long documentId,
                                    OAuth2Authentication principal,
                                    Model model) {
        reservationService.deleteReservation(reservation, principal, documentName, documentId);
        return "redirect:/reservations";
    }

    @PostMapping("/addReservations")
    public String addReservation(@RequestParam("documentId") Long documentId,
                                 @RequestParam("documentName") String documentName,
                                 OAuth2Authentication principal,
                                 Model model) {
        reservationService.addReservation(documentId, documentName, principal);
        return "redirect:/reservations";
    }

}
