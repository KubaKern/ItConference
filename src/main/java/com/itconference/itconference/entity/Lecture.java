package com.itconference.itconference.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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

}
