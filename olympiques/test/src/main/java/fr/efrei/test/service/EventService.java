package fr.efrei.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.efrei.test.repository.EventRepository;

public class EventService {
    @Service
public class eventService {

    @Autowired
    private EventRepository eventRepository;

    public void registerUserForEvent(Long eventId, Long userId) {
        // Vérifier si l'utilisateur est déjà inscrit à un événement à la même date, sinon, enregistrer l'utilisateur pour l'événement
    }
}
}
