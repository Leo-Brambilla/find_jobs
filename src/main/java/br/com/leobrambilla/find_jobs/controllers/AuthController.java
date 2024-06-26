package br.com.leobrambilla.find_jobs.controllers;

import br.com.leobrambilla.find_jobs.entities.User;
import br.com.leobrambilla.find_jobs.repositories.UserRepository;
import br.com.leobrambilla.find_jobs.services.TokenService;
import br.com.leobrambilla.find_jobs.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private final AuthenticationManager authenticationManager;
    @Autowired
    private final TokenService tokenService;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private UserService userService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, TokenService tokenService, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public String login(@RequestBody User loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            var user = (User) authentication.getPrincipal();
            return tokenService.tokenGenerator(user);
        } catch (AuthenticationException e) {
            throw new RuntimeException("Invalid login or password", e);
        }

    }
    @PostMapping(value = "/signup", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> signup(@RequestBody User user, @RequestParam String role) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);
        userService.addRoleToUser(savedUser.getId(), role);

        Map<String, String> response = new HashMap<>();
        response.put("message", "User registered successfully");

        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(response);
    }
}
