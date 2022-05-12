package com.itconference.itconference.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Lecture {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "lecture_name")
    private String lectureName;
    @Column(name = "lecturer")
    private String lecturer;

    @ManyToMany(mappedBy = "registeredLectures")
    private List<User> participants = new ArrayList<>();

    public void addParticipant(User user) {
        participants.add(user);
    }


}
