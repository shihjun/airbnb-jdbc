package com.example.demo.controllers;

import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class UserController {

  @Autowired
  UserRepository userRepository;

  @GetMapping(value = "/allusers", produces = "application/json")
  public List<User> displayUsers() {
    return userRepository.getAllUsers();
  }

  @GetMapping(value = "/users", produces = "application/json")
  public List<User> displayUsersByEmail(@RequestParam String email) {
    return userRepository.getUsersByEmail(email);
  }

  // @GetMapping(path = "/create_user")
  // public void addUser() {
  // userRepository.addUser("Test FN", "Test LN", "test@test.com", "0123456789");
  // }

  // @GetMapping(path = "/update_user")
  // public void updateUser() {
  // userRepository.updateUser("Test FN", "New FN");
  // }

  // @GetMapping(path = "/delete_user")
  // public void deleteUser() {
  // userRepository.deleteUser(7);
  // }

  @PostMapping(value = "/users", produces = "application/json")
  public User createUser(@RequestBody User user) {
    userRepository.createUser(user);
    return user;
  }

  @PostMapping(value = "/users/{id}", produces = "application/json")
  public User updateUser(@RequestBody User user, @PathVariable("id") int id) {
    userRepository.updateUser(id, user);
    return user;
  }

}