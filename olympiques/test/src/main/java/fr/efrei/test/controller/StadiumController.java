package fr.efrei.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.efrei.test.dto.StadiumDto;
import fr.efrei.test.service.StadiumService;

@RestController
@RequestMapping("/api/stadiums")
public class StadiumController {
    @Autowired
    private StadiumService stadiumService;

    @PostMapping
    public ResponseEntity<StadiumDto> createStadium(@RequestBody StadiumDto stadiumDTO) {
        StadiumDto createdStadium = stadiumService.createStadium(stadiumDTO);
        return new ResponseEntity<>(createdStadium, HttpStatus.CREATED);
    }
}