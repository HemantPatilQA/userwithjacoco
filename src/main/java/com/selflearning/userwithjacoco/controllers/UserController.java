package com.selflearning.userwithjacoco.controllers;

import com.selflearning.userwithjacoco.dto.UserDTO;
import com.selflearning.userwithjacoco.entities.Response;
import com.selflearning.userwithjacoco.entities.User;
import com.selflearning.userwithjacoco.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public Response getUsers(){
        List<User> users = userService.getUsers();
        return new Response("Record Count : " + users.size(), Boolean.TRUE);
    }

    @PostMapping("/adduser")
    public Response addUser(@RequestBody UserDTO user){
        userService.addUser(user);
        return new Response(user.getUid() + " inserted", Boolean.TRUE);
    }

    @GetMapping("/getbyaddress/{address}")
    public Response getUsersByAddress(@PathVariable String address){
        List<User> users = userService.getUsersByAddress(address);
        return new Response("Record Count : " + users.size(), Boolean.TRUE);
    }

    @DeleteMapping("/remove")
    public UserDTO removeUser(@RequestBody UserDTO user){
        userService.deleteUser(user);
        return user;
    }
}
