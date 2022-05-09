package com.itconference.itconference.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LectureModel {
    private int id;
    private String lectureName;
    private String lecturer;
}
