package com.hcc.controllers;

import com.hcc.controllers.response.LoginResponse;
import com.hcc.dto.AuthCredentialsRequest;
import com.hcc.services.AuthServiceImpl;
import com.hcc.services.UserDetailServiceImpl;
import com.hcc.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private AuthServiceImpl authService;

    @Autowired
    private UserDetailServiceImpl userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody AuthCredentialsRequest request) {
//        try {
//            Authentication authentication = authService.authenticate(
//                           request.getUsername(), request.getPassword()
//
//            );
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//            UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
//            String token = jwtUtil.generateToken(userDetails);
//
//            Map<String, String> response = new HashMap<>();
//            response.put("token", token);
//            return ResponseEntity.ok(response);
//        } catch (AuthenticationException e) {
//            return ResponseEntity.status(401).body("Invalid credentials");
//        }
//    }
    @CrossOrigin(origins = "*")
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody AuthCredentialsRequest request) {
        Optional<String> token = authService.authenticate(request.getUsername(), request.getPassword());
        if (token.isEmpty()) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        return ResponseEntity.ok(new LoginResponse(token.get()));
    }

//    @PostMapping("/validate")
//    public ResponseEntity<?> validateToken(@RequestBody String token) {
//        String username = null;
//        Map<String, Object> response = new HashMap<>();
//        try {
//            username = jwtUtil.getUsernameFromToken(token);
//            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//            boolean isValid = jwtUtil.validateToken(token, userDetails);
//
//            response.put("valid", isValid);
//            response.put("username", username);
//
//            return ResponseEntity.ok(response);
//        } catch (Exception e) {
//            response.put("valid", false);
//            response.put("error", "Invalid token");
//            return ResponseEntity.status(401).body(response);
//        }
//    }
    @PostMapping("/validate")
    public ResponseEntity<Map<String, Boolean>> validateToken(@RequestHeader("Authorization")
                                                              String authorizationHeader) {

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return ResponseEntity.badRequest().build();
        }
        String token = authorizationHeader.substring(7);
        return ResponseEntity.ok(Collections.singletonMap("isValid", isTokenValid(token)));
    }
    @PostMapping("/register")
    public ResponseEntity<Void> registerNewUser(@RequestBody AuthCredentialsRequest request) {
        userDetailsService.createUser(request.getUsername(), request.getPassword());
        return ResponseEntity.noContent().build();
    }
    private boolean isTokenValid(String token) {
        return jwtUtil.validateToken(token, userDetailsService.loadUserByUsername(jwtUtil.getUsernameFromToken(token)));
    }
}