package com.itconference.itconference.api.controller;

import com.itconference.itconference.model.User;
import com.itconference.itconference.repository.UserRepository;
import com.itconference.itconference.service.ItConferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ItConferenceController {

    private ItConferenceService itConferenceService;
    @Autowired
    private UserRepository userRepository;
    public ItConferenceController(ItConferenceService itConferenceService) {
        this.itConferenceService = itConferenceService;
    }


    @PostMapping("/userAdd")
    public String createUser(@RequestBody User user) {
        try {
            List<User> allUsers = new ArrayList<User>();

            userRepository.findAll().forEach(allUsers::add);

            Optional<User> result = allUsers.stream().findAny().filter(_user -> _user.getName().equals(user.getName()));

            if (result.isPresent())
                return "User with this name already exists";

            User _user = userRepository
                    .save(new User(user.getName(), user.getEmail(), user.getPassword()));

            return "Created user " + user.getName();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Error when creating new user";
        }
    }
}
