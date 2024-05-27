package fr.efrei.test.service;

import fr.efrei.test.UserRepository;
import fr.efrei.test.dto.BilletDto;
import fr.efrei.test.dto.CreateBillet;
import fr.efrei.test.dto.CreateEvent;
import fr.efrei.test.dto.UpdateBillet;
import fr.efrei.test.dto.UpdateEvent;
import fr.efrei.test.model.Billet;
import fr.efrei.test.model.Event;
import fr.efrei.test.model.User;
import fr.efrei.test.repository.BilletRepository;
import fr.efrei.test.repository.EventRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BilletService {
    @Autowired
    private BilletRepository billetRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    public BilletDto buyBillet(Long eventId, Long userId) {
        // Find event and user by their IDs
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        // Ensure the user is not already registered for another event on the same date
        List<Billet> userBillets = billetRepository.findByUserId(userId);
        for (Billet billet : userBillets) {
            if (billet.getEvent().getDate().toLocalDate().equals(event.getDate().toLocalDate())) {
                throw new IllegalStateException("User already registered for another event on the same date");
            }
        }

        // Create and save the new billet
        Billet billet = new Billet();
        billet.setEvent(event);
        billet.setUser(user);
        billet = billetRepository.save(billet);

        // Convert to BilletDTO and return
        BilletDto billetDTO = new BilletDto();
        billetDTO.setId(billet.getId());
        billetDTO.setEventId(billet.getEvent().getId());
        billetDTO.setUserId(billet.getUser().getId());

        return billetDTO;
    }

      public List<BilletDto> getUserBillets(Long userId) {
        List<Billet> billets = billetRepository.findByUserId(userId);
        return billets.stream().map(billet -> {
            BilletDto billetDTO = new BilletDto();
            billetDTO.setId(billet.getId());
            billetDTO.setEventId(billet.getEvent().getId());
            billetDTO.setUserId(billet.getUser().getId());
            return billetDTO;
        }).collect(Collectors.toList());
    }

    public void deleteBillet(Long billetId) {
        billetRepository.deleteById(billetId);
    }
}
