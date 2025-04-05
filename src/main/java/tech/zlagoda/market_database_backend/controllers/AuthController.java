package tech.zlagoda.market_database_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import tech.zlagoda.market_database_backend.pojos.AuthRequest;
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
    public ResponseEntity<String> login(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );
        if (authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.OK).body(jwtService.generateToken(authRequest.getUsername()));
        } else {
            throw new UsernameNotFoundException("Invalid login information");
        }
    }
}
