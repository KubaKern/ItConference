package com.itconference.itconference.api.controller;

import com.itconference.itconference.api.model.Information;
import com.itconference.itconference.entity.Lecture;
import com.itconference.itconference.entity.User;
import com.itconference.itconference.repository.UserRepository;
import com.itconference.itconference.service.ItConferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ItConferenceController {
    @Autowired
    //private ItConferenceService itConferenceService;
    private UserRepository userRepository;

    public ItConferenceController(ItConferenceService itConferenceService) {
        //this.itConferenceService = itConferenceService;
        //this.userRepository = userRepository;
    }


    @PostMapping("/userAdd")
    public Information createUser(@RequestBody User user) {
        try {
            List<User> allUsers = new ArrayList<User>();

            userRepository.findAll().forEach(allUsers::add);

            Optional<User> result = allUsers.stream().findAny().filter(_user -> _user.getName().equals(user.getName()));

            if (result.isPresent())
                return new Information("User with this name already exists","400");

            User _user = userRepository
                    .save(new User(user.getName(), user.getEmail(), user.getPassword()));

            return new Information("Created user " + user.getName(),"200");
        } catch (Exception e) {
           // System.out.println(e.getMessage());
            return new Information(e.getMessage(),"500");
        }
    }

    @GetMapping("/userEmailChange/{name},{newEmail}")
    public Information changeUserEmail(@PathVariable String name, @PathVariable String newEmail) {
        try {
            User _user = userRepository.findByName(name);
            _user.setEmail(newEmail);
            userRepository.save(_user);
            return new Information("Email changed succesfuly.","200");
        }
        catch (Exception e){
            return new Information(e.getMessage(),"500");
        }
    }

    @GetMapping("/userLectures/{name}")
    public String registerLecture(@PathVariable String name) {
        try {
            User user = userRepository.findByName(name);

            StringBuilder builder = new StringBuilder();

            for (Lecture lecture : user.getRegisteredLectures()) {
                builder.append(lecture.getLectureName() + "\n");
            }
            return builder.toString();
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }

}
