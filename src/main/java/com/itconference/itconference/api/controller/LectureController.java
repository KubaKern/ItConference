package com.itconference.itconference.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itconference.itconference.entity.Lecture;
import com.itconference.itconference.repository.LectureRepository;
import com.itconference.itconference.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public String generateRaport() throws JsonProcessingException {
        List<Lecture> lectures = lectureRepository.findAll();
        double numberOfUsers = userRepository.findAll().size();
        Map<String, String> usersInLectures = new HashMap<>();
        Map <String,String> usersInBlocks = new HashMap<>();
        for (Lecture lecture : lectures) {
            usersInLectures.put(lecture.getLectureName(), (lecture.getParticipants().size())/5.0*100 + "%");
        }
        String[] blocks = {"First", "Second", "Third"};
        for (int i=0;i<3;i++) {
            int usersByBlock = lectures.get(i).getParticipants().size() + lectures.get(i+3).getParticipants().size() + lectures.get(i+6).getParticipants().size();
            usersInBlocks.put(blocks[i],usersByBlock/numberOfUsers*100.0 + "%");
        }

        ObjectMapper mapper = new ObjectMapper();
        String jsonResult = mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(usersInLectures);
        String _jsonResults = mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(usersInBlocks);
        return jsonResult + _jsonResults;
    }

}
