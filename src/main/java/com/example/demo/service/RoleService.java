package main.java.com.example.demo.service;


import main.java.com.example.demo.entity.Role;
import main.java.com.example.demo.respository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role saveRole(Role role){
        try{
          return  roleRepository.save(role);
        }catch (Exception e){
            throw new RuntimeException("Failed to save role:"+ e.getMessage());
        }
    }

    public List<Role> getRoles(){
        try {
            return roleRepository.findAll();
        }catch (Exception e){
            throw  new RuntimeException("Failed to get all roles:"+e.getMessage());
        }
    }
}
