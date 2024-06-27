package main.java.com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long UserID;
    private String Name;

    public long getUserID() { return  UserID;}
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setUserID(long userID) { UserID = userID;}
}