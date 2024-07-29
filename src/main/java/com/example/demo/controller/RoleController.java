package main.java.com.example.demo.controller;

import main.java.com.example.demo.entity.Role;
import main.java.com.example.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping
    public ResponseEntity<Role> saveRole(@RequestBody Role role){
        Role savedRole = roleService.saveRole(role);
        return  ResponseEntity.ok(savedRole);
    }

    @GetMapping
    public ResponseEntity<List <Role>> getRoles(){
        List<Role> roles = roleService.getRoles();
        return ResponseEntity.ok(roles);
    }
}
