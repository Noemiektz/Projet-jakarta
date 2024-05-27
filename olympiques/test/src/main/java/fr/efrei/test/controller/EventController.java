package fr.efrei.test.controller;

import fr.efrei.test.dto.CreateEvent;
import fr.efrei.test.dto.EventDto;
import fr.efrei.test.dto.UpdateEvent;
import fr.efrei.test.model.Event;
import fr.efrei.test.model.Student;
import fr.efrei.test.repository.EventRepository;
import fr.efrei.test.service.EventService;
import fr.efrei.test.service.EventService;
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
@RequestMapping("/api/events")
public class EventController {
    @Autowired
    private EventService eventService;

    @PostMapping
    public ResponseEntity<EventDto> createEvent(@RequestBody EventDto eventDTO) {
        EventDto createdEvent = eventService.createEvent(eventDTO);
        return new ResponseEntity<>(createdEvent, HttpStatus.CREATED);
    }
}

