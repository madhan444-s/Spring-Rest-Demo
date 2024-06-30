package main.java.com.example.demo.controller;

import main.java.com.example.demo.entity.User;
import main.java.com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List <User>> findAllUsers() {
        List<User> users = userService.fetchAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getProductById(@PathVariable Long id){
        Optional<User> optionalUser = userService.fetchUserById(id);
        return  optionalUser.map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user){
        User savedUser = userService.saveUser(user);
        return ResponseEntity.ok(savedUser);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user){
        Optional<User> updatedOptionalUser = userService.updateUser(id, user);
        return  updatedOptionalUser.map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());

    }

}
