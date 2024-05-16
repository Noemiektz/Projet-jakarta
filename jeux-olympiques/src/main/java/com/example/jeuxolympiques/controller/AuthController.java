package com.example.jeuxolympiques.controller;

import com.example.jeuxolympiques.dto.LoginDto;
import com.example.jeuxolympiques.dto.LoginResponse;
import com.example.jeuxolympiques.dto.RegisterDto;
import com.example.jeuxolympiques.model.User;
import com.example.jeuxolympiques.service.AuthService;
import com.example.jeuxolympiques.security.JwtService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthController {
	private final JwtService jwtService;

	private final AuthService authenticationService;

	public AuthController(JwtService jwtService, AuthService authenticationService) {
		this.jwtService = jwtService;
		this.authenticationService = authenticationService;
	}

	@PostMapping("/signup")
	public ResponseEntity<User> register(@RequestBody RegisterDto registerUserDto) {
		User registeredUser = authenticationService.signup(registerUserDto);
		return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<LoginResponse> authenticate(
			@RequestBody LoginDto loginUserDto) {
		User authenticatedUser = authenticationService.authenticate(loginUserDto);

		String jwtToken = jwtService.generateToken(authenticatedUser);

		LoginResponse loginResponse = new LoginResponse();
		loginResponse.setToken(jwtToken);
		loginResponse.setExpiresIn(jwtService.getExpirationTime());

		return new ResponseEntity<>(loginResponse, HttpStatus.OK);
	}
}
