package main.java.com.example.demo.entity;

import jakarta.persistence.*;
import main.java.com.example.demo.utils.PasswordUtils;

@Entity
@Table(name = "user")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid")
    private Long userId;
    private String name;
    private String email;
    private String password;

    public long getUserID() { return  userId;}
    public String getName() {
        return name;
    }
    public String getEmail() { return email; }
    public String getPassword() { return password; }

    public void setUserID(long userId) { this.userId = userId;}
    public void setName(String name) {
        this.name = name;
    }
    public void setEmail(String email) {this.email = email; }
    public void setPassword(String password) {this.password = PasswordUtils.encodePassword(password)    ; }
}