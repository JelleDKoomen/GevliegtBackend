package nl.Gevliegt.Gevliegt.controller;

import nl.Gevliegt.Gevliegt.model.User;
import nl.Gevliegt.Gevliegt.service.UserService;
import nl.Gevliegt.Gevliegt.util.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class LoginController {
    private final UserService userService;

    private final JwtUtil jwtUtil;

    public LoginController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody User loginRequest) {
        if (userService.validateUser(loginRequest.getUsername(), loginRequest.getPassword())) {
            String token = jwtUtil.generateToken(loginRequest.getUsername());
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody User user) {
        if (userService.findByUsername(user.getUsername()) != null) {
            return ResponseEntity.status(409).body("Username already exists");
        }
        userService.createUser(user.getUsername(), user.getPassword(), user.getEmail(), user.getFirstName(), user.getLastName(),
                user.getBirthdate(), user.getProfilePictureUrl());
        return ResponseEntity.ok("User registered successfully");
    }
}