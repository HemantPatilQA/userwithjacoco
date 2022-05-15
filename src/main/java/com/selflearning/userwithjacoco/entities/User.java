package com.selflearning.userwithjacoco.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long uid;
    private String username;
    private int age;
    private String address;

    /*public User(String username, int age, String address) {
        this.username = username;
        this.age = age;
        this.address = address;
    }*/
}
