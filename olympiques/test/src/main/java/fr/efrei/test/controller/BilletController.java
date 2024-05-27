package fr.efrei.test.controller;

import java.nio.file.attribute.UserPrincipal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import fr.efrei.test.service.BilletService;
import fr.efrei.test.service.BilletService.TicketService;

public class BilletController {
    @Autowired
    private BilletService billetService;

    @PostMapping("/buy")
    public ResponseEntity<?> buyTickets(@RequestBody buyBilletsRequest buyTicketsRequest, @AuthenticationPrincipal UserPrincipal userPrincipal) {
        try {
            billetService.buyTickets(buyTicketsRequest, userPrincipal.getId());
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to buy tickets: " + e.getMessage());
        }
    }

    
}
