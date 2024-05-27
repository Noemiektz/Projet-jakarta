package fr.efrei.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.efrei.test.dto.EventDto;
import fr.efrei.test.model.Event;
import fr.efrei.test.repository.EventRepository;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private StadiumRepository stadiumRepository;

    public EventDto createEvent(EventDto eventDTO) {
        Event event = new Event();
        event.setName(eventDTO.getName());
        event.setDate(eventDTO.getDate());

        Stadium stadium = stadiumRepository.findById(eventDTO.getStadiumId())
                .orElseThrow(() -> new ResourceNotFoundException("Stadium not found"));
        event.setStadium(stadium);

        event = eventRepository.save(event);

        eventDTO.setId(event.getId());
        return eventDTO;
    }
}
