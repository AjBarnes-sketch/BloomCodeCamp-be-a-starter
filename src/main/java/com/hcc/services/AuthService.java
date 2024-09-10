package com.hcc.services;

import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public interface AuthService {

    Optional<String> authenticate(String username, String password);
}