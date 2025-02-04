package main.java.com.example.demo.service;


import main.java.com.example.demo.entity.User;
import main.java.com.example.demo.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Cacheable(value = "allUsersCache", key = "#root.methodName")
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

    public  Optional<User> updateUser(Long id, User updateUser){
        try{
            Optional<User> existingOptionalUser = userRepository.findById(id);
            if(existingOptionalUser.isPresent()){
                User existingUser = existingOptionalUser.get();
                existingUser.setName(updateUser.getName());
                User savedUser = userRepository.save(existingUser);
                return Optional.of(savedUser);
            }else{
                return  Optional.empty();
            }

        }catch (Exception e){
            throw new RuntimeException("Failed to update product: " + e.getMessage());
        }
    }
}
