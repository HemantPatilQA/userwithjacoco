package com.selflearning.userwithjacoco.services;

import com.selflearning.userwithjacoco.entities.User;
import com.selflearning.userwithjacoco.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    public UserRepository userRepository;

    public List<User> getUsers(){
        List<User> users = userRepository.findAll();
        System.out.println("Gettiing data from DB : " + users);
        return users;
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getUsersByAddress(String address) {
        return userRepository.findAllByAddress(address);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }
}
