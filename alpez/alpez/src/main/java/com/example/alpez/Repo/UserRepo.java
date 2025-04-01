package com.example.alpez.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.alpez.Entity.UserEntity;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Integer> {

}