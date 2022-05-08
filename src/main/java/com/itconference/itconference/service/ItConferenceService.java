package com.itconference.itconference.service;

import com.itconference.itconference.api.dto.UserDto;
import com.itconference.itconference.model.User;
import com.itconference.itconference.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItConferenceService {
    public String users(UserDto userDto) {

        return userDto.getName();
    }

    UserRepository userRepository;

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
            return "Error when creating new user";
        }
    }

//    @GetMapping("/schedule")
//    public String conferenceSchedule() {
//        try {
//            return Files.readString(Path.of("schedule.txt"), StandardCharsets.US_ASCII);
//        }
//        catch (Exception e) {
//            return "No schedule found.";
//        }
//    }
}
