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

import java.util.List;

@RestController
public class LectureController {
    @Autowired
    LectureRepository lectureRepository;
    UserRepository userRepository;



//    @GetMapping("/test")
//    public String test() {
//        try {
//            List<Lecture> lectures = lectureRepository.findAll();
//            // return lectures.get(0).getParticipants();
//
//        }
//        catch (Exception e) {
//            return e.getMessage();
//        }
//        return "test";
//    }

}
