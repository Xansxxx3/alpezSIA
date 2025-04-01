package com.example.alpez.Service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.alpez.Entity.UserEntity;
import com.example.alpez.Repo.UserRepo;




@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
    
    

   //CREATE
    public UserEntity saveUser(UserEntity user) {
        return userRepo.save(user);
    }


    //READ
    public List<UserEntity> getAllUsers(){
        return userRepo.findAll();
    }
    public Optional<UserEntity> getUserByUserId(int userId) {
        return userRepo.findById(userId);
    }


    //UPDATE
    public UserEntity updateUser(int userId, UserEntity updateUser) {
        UserEntity user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("Reservation " + userId + " not found!"));
        

                user.setName(updateUser.getName());
                user.setEmail(updateUser.getEmail());
                user.setPassword(updateUser.getPassword());
        return userRepo.save(user);
    }

    //DELETE
    public String deleteUser(int userId) {
        Optional<UserEntity> user = userRepo.findById(userId);
        if (user.isPresent()) {
            userRepo.deleteById(userId);
            return "User has been successfully deleted!";
        } else {
            return "User with ID " + userId + " not found!";
        }
    }
    

    
    
}