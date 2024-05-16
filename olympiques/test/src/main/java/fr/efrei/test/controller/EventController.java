package fr.efrei.test.controller;

import fr.efrei.test.dto.CreateEvent;
import fr.efrei.test.dto.UpdateEvent;
import fr.efrei.test.model.Event;
import fr.efrei.test.model.Student;
import fr.efrei.test.service.event;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("events")
@EnableMethodSecurity
public class EventController {

	private final EventService service;

	@Autowired
	public EventController(EventService service) {
		this.event = service;
	}

	@GetMapping
	public ResponseEntity<List<Student>> findAll() {
		return new ResponseEntity<>(event.findAllevents(), HttpStatus.OK);
	}

	@GetMapping("/{uuid}")
	public ResponseEntity<Event> findOneById(@PathVariable String uuid) {
		Event event = service.findStudentById(uuid);
		if(event != null) {
			return new ResponseEntity<>(service.findStudentById(uuid), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping
	public ResponseEntity<Event> save(@Valid @RequestBody CreateEvent event) {
		Student createdEvent = service.create(Event);
		return new ResponseEntity<>(createdEvent, HttpStatus.CREATED);
	}

	@DeleteMapping("/{uuid}")
	public ResponseEntity<?> delete(@PathVariable String uuid) {
		boolean isDeleted = service.delete(uuid);
		if(isDeleted) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PutMapping("/{uuid}")
	public ResponseEntity<?> mettreAJourTotalement(
			@PathVariable String uuid,
			@RequestBody UpdateEvent event) {
		boolean isUpdated = service.update(uuid, event);
		if(isUpdated) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PatchMapping("/{uuid}")
	public ResponseEntity<?> mettreAjourPartiellement(
			@PathVariable String uuid,
			@RequestBody UpdateEvent student) {
		boolean isUpdated = service.updatePartielle(uuid, student);
		if(isUpdated) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
