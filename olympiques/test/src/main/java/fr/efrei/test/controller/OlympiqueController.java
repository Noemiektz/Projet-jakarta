package fr.efrei.test.controller;

import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jeux-olympiques")
@EnableMethodSecurity
public class OlympiqueController {
    
}
