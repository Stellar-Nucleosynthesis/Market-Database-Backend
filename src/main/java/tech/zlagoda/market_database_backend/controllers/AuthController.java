package tech.zlagoda.market_database_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import tech.zlagoda.market_database_backend.pojos.AuthResponse;
import tech.zlagoda.market_database_backend.pojos.Credentials;
import tech.zlagoda.market_database_backend.services.JwtService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthController(JwtService jwtService, AuthenticationManager authenticationManager){
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody Credentials credentials) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword())
        );
        if (authentication.isAuthenticated()) {
            AuthResponse response = new AuthResponse();
            response.setToken(jwtService.generateToken(credentials.getUsername()));
            response.setRole(authentication.getAuthorities().iterator().next().getAuthority());
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            throw new UsernameNotFoundException("Invalid login information");
        }
    }
}
