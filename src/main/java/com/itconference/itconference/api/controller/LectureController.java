package com.itconference.itconference.api.controller;

import com.itconference.itconference.entity.Lecture;
import com.itconference.itconference.entity.User;
import com.itconference.itconference.repository.LectureRepository;
import com.itconference.itconference.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class LectureController {
    @Autowired
    LectureRepository lectureRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/generateRaport")
    public String generateRaport() {
        StringBuilder builder = new StringBuilder();
        StringBuilder builderBlocks = new StringBuilder();
        List<Lecture> lectures = lectureRepository.findAll();
        double numberOfUsers = userRepository.findAll().size();
        Map<String, String> usersInLectures = new HashMap<>();
        Map <String,String> usersInBlocks = new HashMap<>();
        for (Lecture lecture : lectures) {
            usersInLectures.put(lecture.getLectureName(), (lecture.getParticipants().size())/5*10 + "%");
            String percent = (lecture.getParticipants().size())/5.0*100 + "%";
            builder.append(lecture.getLectureName()).append(" ").append(percent).append("\n");
        }
        String[] blocks = {"First", "Second", "Third"};
        for (int i=0;i<3;i++) {
            int usersByBlock = lectures.get(i).getParticipants().size() + lectures.get(i+3).getParticipants().size() + lectures.get(i+6).getParticipants().size();
            String percent = (usersByBlock / numberOfUsers)*100 + "%";
            usersInBlocks.put(blocks[i],usersByBlock/numberOfUsers*100.0 + "%");
            builder.append(blocks[i]).append(" ").append(percent).append("\n");
        }

        return builder.toString();
    }

}
