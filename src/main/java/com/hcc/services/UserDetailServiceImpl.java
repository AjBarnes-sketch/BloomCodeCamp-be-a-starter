package com.hcc.services;

import com.hcc.entities.User;
import com.hcc.repositories.UserRepository;
import com.hcc.utils.CustomPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepo;
    @Autowired
    private CustomPasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOpt = userRepo.findByUsername(username);
        return userOpt.orElseThrow(() -> new UsernameNotFoundException("Invalid Credentials"));
    }
    public void createUser(String username, String password) {
        User user = new User();
        if(userRepo.findByUsername(username).isPresent()) {
            throw new RuntimeException("This username already exists.");
        }
        user.setUsername(username);
        user.setPassword(passwordEncoder.getPasswordEncoder().encode(password));
        userRepo.save(user);
    }
    public void deleteUser(User user) {
        //TODO add the role check so not everyone can delete a user
        userRepo.delete(user);
    }
}