package fr.efrei.test.service;

import fr.efrei.test.dto.CreateBillet;
import fr.efrei.test.dto.CreateEvent;
import fr.efrei.test.dto.UpdateBillet;
import fr.efrei.test.dto.UpdateEvent;
import fr.efrei.test.model.Billet;
import fr.efrei.test.model.Event;
import fr.efrei.test.repository.BilletRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BilletService {

	private final BilletRepository repository;

	@Autowired
	public BilletService(BilletRepository repository) {
		this.repository = repository;
	}

	public List<Event> findAllEvents() {
		return repository.findAllByDeletedAtNull();
	}

	public Event findStudentById(String uuid) {
		return repository.findOneByUuid(uuid).orElse(null);
	}

	public Event create(CreateEvent event) {
		// ici je suis dans la DTO
		//
		Event eventACreer = new Event(
				event.getName(),
				
		);
		// je suis dans une entité
		return repository.save(eventACreer);
	}



	public boolean update(String uuid, UpdateEvent student) {
		Event eventAModifier = findEventById(uuid);

		if(eventAModifier != null) {
			eventAModifier.setName(eventAModifier.getName());
			repository.save(Billet eventAModifier);
			return true;
		}
		return false;
	}

	public boolean updatePartielle(String uuid, UpdateEvent event) {
		Event eventAModifier = findEventById(uuid);

		if(eventAModifier != null) {
		
			if(!event.getName().isEmpty()) {
				eventAModifier.setName(event.getName());
			}
			repository.save(Billet eventAModifier);
			return true;
		}
		return false;
	}
}
