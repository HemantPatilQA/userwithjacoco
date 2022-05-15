package com.selflearning.userwithjacoco.controllers;

import com.selflearning.userwithjacoco.entities.Response;
import com.selflearning.userwithjacoco.entities.User;
import com.selflearning.userwithjacoco.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public Response getUsers(){
        //return new ResponseEntity<List<User>>(userService.getUsers(), HttpStatus.OK);
        List<User> users =  userService.getUsers();
        return new Response("Record Count : " + users.size(), Boolean.TRUE);
    }

    @PostMapping("/adduser")
    public Response addUser(@RequestBody User user){
        //return new ResponseEntity<User>(userService.addUser(user), HttpStatus.CREATED);
        userService.addUser(user);
        return new Response(user.getUid() + " inserted", Boolean.TRUE);
    }

    @GetMapping("/getbyaddress/{address}")
    public List<User> getUsersByAddress(@PathVariable String address){
        //return new ResponseEntity<List<User>>(userService.getUsersByAddress(address), HttpStatus.OK);
        return userService.getUsersByAddress(address);
    }

    @DeleteMapping("/remove")
    public User removeUser(@RequestBody User user){
        userService.deleteUser(user);
        return user;
    }
}
