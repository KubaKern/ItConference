package com.itconference.itconference.api.controller;

import com.itconference.itconference.entity.User;
import com.itconference.itconference.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired

    UserRepository userRepository;

    @GetMapping("/prelectionUsers")
    public String getAllUsers() {
        try {
            List<User> users = userRepository.findAll();
            if (users.isEmpty()) {
                return "no users found";
            }

            StringBuilder allUsers = new StringBuilder();

            for (User user : users) {
                allUsers.append(user.getName() + " " + user.getEmail() + "\n");
            }

            return allUsers.toString();
        } catch (Exception e) {
            return "Error occurred";
        }
    }
    @PostMapping("/userLogin")
    public String userLogin(@RequestBody(required = false) User user) {
        try {
            List<User> users = new ArrayList<User>();
            if (user.getName() == null || user.getPassword() == null)
                return "You must insert your name and password to log in";
            else
                userRepository.findByName(user.getName()).forEach(users::add);

            Optional<User> result = users.stream().findAny().filter(_user -> _user.getName().equals(user.getName()) && _user.getPassword().equals(user.getPassword()));

            if (result.isPresent())
                return "Succesfully logged in.";
            else
                return "Wrong credentials.";


        } catch (Exception e) {
            return "Error occurred";
        }

    }

    @GetMapping("/schedule")
    public String conferenceSchedule() {
        try {
            return Files.readString(Path.of("src/main/resources/schedule.txt"), StandardCharsets.US_ASCII);
        }
        catch (Exception e) {
            return "No schedule found.";
        }
    }

}
