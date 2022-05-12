package com.itconference.itconference.service;

import com.itconference.itconference.api.model.Information;
import com.itconference.itconference.api.model.UserModel;
import com.itconference.itconference.entity.User;
import com.itconference.itconference.repository.LectureRepository;
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
    LectureRepository lectureRepository;
    @PostMapping("/userAdd")
    public Information createUser(@RequestBody User user) {
        try {
            List<User> allUsers = new ArrayList<User>();

            userRepository.findAll().forEach(allUsers::add);

            Optional<User> result = allUsers.stream().findAny().filter(_user -> _user.getName().equals(user.getName()));

            if (result.isPresent())
                return new Information("User with this name already exists","400");

            userRepository.save(new User(user.getName(), user.getEmail(), user.getPassword()));

            return new Information("Created user " + user.getName(),"200");
        } catch (Exception e) {
            return new Information("Error when creating new user","500");
        }
    }
}
