package fr.efrei.test.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String uuid;

	private String name;

	@OneToMany
	private Set<Event> events = new HashSet<>();

	public String getName() {
		return name;
	}

	public Set<Event> getEvents() {
		return events;
	}

	public void setName(String name) {
		this.name = name;
	}


}
