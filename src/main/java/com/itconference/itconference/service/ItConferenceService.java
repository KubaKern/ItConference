package com.itconference.itconference.service;

import com.itconference.itconference.api.model.UserModel;
import com.itconference.itconference.entity.User;
import com.itconference.itconference.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItConferenceService {
    public String users(UserModel userModel) {

        return userModel.getName();
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

            userRepository.save(new User(user.getName(), user.getEmail(), user.getPassword()));

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
