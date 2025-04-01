package com.example.alpez.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.alpez.Entity.UserEntity;
import com.example.alpez.Service.UserService;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/print")
    public String print(){
        return "I miss you!";
    }

    //CREATE
    @PostMapping("/save")
    public UserEntity saveUser(@RequestBody UserEntity user){
        return userService.saveUser(user);
    }

    //READ
    @GetMapping("/getAll")
    public List<UserEntity> getAllUsers(){
        return userService.getAllUsers();
    }
    @GetMapping("/getUserById")
    public Optional<UserEntity> getUserbyUserId(int userId){
        return userService.getUserByUserId(userId);
    }
   
    //UPDATE
    @PutMapping("/update/{userId}")
    public UserEntity updateUser(@PathVariable int userId, @RequestBody UserEntity user){
        return userService.updateUser(userId, user);
    }

    //DELETE
    @DeleteMapping("/delete/{userId}")
    public String deleteUser(@PathVariable int userId){
        return userService.deleteUser(userId);
    }
}
