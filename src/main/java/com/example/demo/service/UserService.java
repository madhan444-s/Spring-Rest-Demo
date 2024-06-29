package main.java.com.example.demo.service;

import main.java.com.example.demo.entity.User;
import main.java.com.example.demo.respository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> fetchAllUsers(){
        try {
              return userRepository.findAll();
        }catch (Exception e){
            throw new RuntimeException("Failed to fetch all users:"+ e.getMessage());
        }
    }

    public Optional<User> fetchUserById(Long id){
        try {
            return userRepository.findById(id);
        }catch (Exception e){
            throw new RuntimeException("Failed to fetch user by id:"+ e.getMessage());
        }
    }

    public User saveUser(User user){
        try{
            return  userRepository.save(user);
        }catch (Exception e){
            throw new RuntimeException("Failed to save user:"+ e.getMessage());
        }
    }
}
