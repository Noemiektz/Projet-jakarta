package fr.efrei.test.service;

import org.springframework.beans.factory.annotation.Autowired;

import fr.efrei.test.dto.StadiumDto;
import fr.efrei.test.model.Stadium;
import fr.efrei.test.repository.StadiumRepository;

public class StadiumService {
     @Autowired
    private StadiumRepository stadiumRepository;

    public StadiumDto createStadium(StadiumDto stadiumDTO) {
        Stadium stadium = new Stadium();
        stadium.setName(stadiumDTO.getName());

        stadium = stadiumRepository.save(stadium);

        stadiumDTO.setId(stadium.getId());
        return stadiumDTO;
    }
}
