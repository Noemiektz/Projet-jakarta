package fr.efrei.test.dto;

import jakarta.validation.constraints.*;


public class CreateEvent {

	@NotBlank
	@Size(min = 1, max = 25)
	private String name;

	
	public CreateEvent(String name) {
		this.name = name;
		
	}

	public String getName() {
		return name;
	}

	
}
