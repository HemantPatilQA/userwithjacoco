package com.selflearning.userwithjacoco.services;

import com.selflearning.userwithjacoco.dto.UserDTO;
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
        return userRepository.findAll();
    }

    public UserDTO addUser(UserDTO userDTO) {
        User user = new User();
        user.setAddress(userDTO.getAddress());
        user.setUsername(userDTO.getUsername());
        user.setAge(userDTO.getAge());
        userRepository.save(user);
        userDTO.setUid(userDTO.getUid());
        return userDTO;
    }

    public List<User> getUsersByAddress(String address) {
        return userRepository.findAllByAddress(address);
    }

    public void deleteUser(UserDTO userDTO) {
        User user = new User();
        user.setUid(userDTO.getUid());
        user.setUsername(userDTO.getUsername());
        user.setAge(userDTO.getAge());
        user.setAddress(user.getAddress());
        userRepository.delete(user);
    }
}
