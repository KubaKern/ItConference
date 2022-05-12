package com.itconference.itconference.api.controller;

import com.itconference.itconference.api.model.Information;
import com.itconference.itconference.entity.Lecture;
import com.itconference.itconference.entity.User;
import com.itconference.itconference.repository.LectureRepository;
import com.itconference.itconference.repository.UserRepository;
import com.itconference.itconference.service.ItConferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.swing.plaf.IconUIResource;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class ItConferenceController {
    @Autowired
    //private ItConferenceService itConferenceService;
    private UserRepository userRepository;
    @Autowired
    private LectureRepository lectureRepository;
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
    public String checkRegistration(@PathVariable String name) {
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

    @GetMapping("/lectureSign/{name},{email},{id}")
    public Information signForLecture(@PathVariable String name, @PathVariable String email,@PathVariable int id) {
        try {
            User user = userRepository.findByName(name);
            Lecture lecture = lectureRepository.findById(id);

            int block = id %3;
            for (Lecture _lecture : user.getRegisteredLectures()) {
                if (_lecture.getId() % 3 == block)
                    return new Information("You are already registered in another lecture in this time.","202");
            }

            if (lecture.getParticipants().size() == 5)
                return new Information("This lecture hax maximum limit for participants.","202");
            else {
                user.signToLecture(lecture);
                lecture.addParticipant(user);
                userRepository.save(user);
                lectureRepository.save(lecture);

                String timeStamp = ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME);
                try {
                    FileWriter fileWriter = new FileWriter("src/main/resources/powiadomienia.txt");
                    fileWriter.append("Date: ").append(timeStamp)
                            .append("\nE-mail: ").append(email).append("\nYou have successfully registered for lecture: ")
                            .append(lecture.getLectureName()).append("\n");
                    fileWriter.close();
                }
                catch (Exception e) {
                    return new Information(e.getMessage(),"500");
                }
            }
            return new Information("Added to lecture.","200");
        }
        catch (Exception e) {
            return new Information(e.getMessage(),"500");
        }
    }

    @GetMapping("/deleteRegistration/{name},{id}")
    public Information deleteRegistration(@PathVariable String name,@PathVariable int id) {
        User user = userRepository.findByName(name);
        Lecture lecture = lectureRepository.findById(id);
        try {
            user.setRegisteredLectures(user.getRegisteredLectures().stream().filter(object->object.getId() != id).collect(Collectors.toList()));
            userRepository.save(user);

            lecture.setParticipants(lecture.getParticipants().stream().filter(object->object.getName() != name).collect(Collectors.toList()));
            lectureRepository.save(lecture);
        }
        catch (Exception e) {
            return new Information(e.getMessage(),"500");
        }
        return new Information("Succesfully deleted reservation.","200");
    }
}
