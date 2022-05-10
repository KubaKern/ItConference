package com.itconference.itconference.api.controller;

import com.itconference.itconference.api.model.Information;
import com.itconference.itconference.entity.User;
import com.itconference.itconference.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Information getAllUsers() {
        try {
            List<User> users = userRepository.findAll();
            if (users.isEmpty()) {
                return new Information("No users found.", "404");
            }

            StringBuilder allUsers = new StringBuilder();

            for (User user : users) {
                allUsers.append(user.getName() + " " + user.getEmail() + "\n");
            }

            return new Information(allUsers.toString(),"200");
        } catch (Exception e) {
            return new Information(e.getMessage(),"500");
        }
    }
    @PostMapping("/userLogin")
    public Information userLogin(@RequestBody(required = false) User user) {
        try {
            List<User> users = new ArrayList<User>();
            if (user.getName() == null || user.getPassword() == null)
                return new Information("You must insert your name and password to log in","400");
            else
                userRepository.findByName(user.getName()).forEach(users::add);

            Optional<User> result = users.stream().findAny().filter(_user -> _user.getName().equals(user.getName()) && _user.getPassword().equals(user.getPassword()));

            if (result.isPresent())
                return new Information("Succesfully logged in.","200");
            else
                return new Information("Wrond credentials.","400");


        } catch (Exception e) {
            return new Information(e.getMessage(),"500");
        }

    }

    @GetMapping("/schedule")
    public Information conferenceSchedule() {
        try {
            return new Information(Files.readString(Path.of("src/main/resources/schedule.txt"), StandardCharsets.US_ASCII),"200");
        }
        catch (Exception e) {
            return new Information("No schedule found.","500");
        }
    }

}
