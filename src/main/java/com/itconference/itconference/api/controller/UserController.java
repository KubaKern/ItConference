package com.itconference.itconference.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itconference.itconference.api.model.Information;
import com.itconference.itconference.entity.User;
import com.itconference.itconference.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@RestController
public class UserController {
    @Autowired

    UserRepository userRepository;

    @GetMapping("/prelectionUsers")
    public String getAllUsers() {
        try {
            List<User> users = userRepository.findAll();
            Map<String,String> map = new HashMap<>();
            if (users.isEmpty()) {
                //return new Information("No users found.", "404");
                return "{\"No users found\":404}";
            }
            for (User user : users) {
               // allUsers.append(user.getName() + " " + user.getEmail() + "\n");
                map.put(user.getName(),user.getEmail());
            }

            ObjectMapper mapper = new ObjectMapper();

            return mapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(map);
        } catch (Exception e) {
            return e.getMessage();
        }
    }
    @PostMapping("/userLogin")
    public Information userLogin(@RequestBody(required = false) User user) {
        try {
            List<User> users = new ArrayList<User>();
            if (user.getName() == null || user.getPassword() == null)
                return new Information("You must insert your name and password to log in","400");
            else
                userRepository.findAll().forEach(users::add);

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
    public String conferenceSchedule() {
        try {
            return Files.readString(Path.of("src/main/resources/schedule.txt"), StandardCharsets.US_ASCII);
        }
        catch (Exception e) {
            return "{\"No schedule found\":404}";

        }
    }

}
