package main.java.com.example.demo.service;

import main.java.com.example.demo.entity.User;
import main.java.com.example.demo.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {


    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{

        Optional<User> userOptional = userRepository.findByEmail(email);

        // Logging for debugging purposes
        System.out.println("Loading user by email: " + email);
        userOptional.ifPresentOrElse(
                user -> System.out.println("User found: " + user.getEmail()),
                () -> System.out.println("User not found with email " + email)
        );

        User user = userOptional.orElseThrow(() ->
                new UsernameNotFoundException("User not found with email: " + email)
        );

        return  new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }
}
