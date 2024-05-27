package fr.efrei.test.controller;

import fr.efrei.test.dto.CreateEvent;
import fr.efrei.test.dto.UpdateEvent;
import fr.efrei.test.model.Event;
import fr.efrei.test.model.Student;
import fr.efrei.test.repository.EventRepository;
import fr.efrei.test.service.event;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipal;
import java.util.List;

@RestController
@RequestMapping("events")
@EnableMethodSecurity
public class EventController {

    @Autowired
    private eventService eventService;

    @PostMapping("/{eventId}/register")
    public ResponseEntity<?> registerForEvent(@PathVariable Long eventId,
            @AuthenticationPrincipal UserPrincipal userPrincipal) {
        try {
            eventService.registerUserForEvent(eventId, userPrincipal.getId());
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to register for event: " + e.getMessage());
        }
    }

}

