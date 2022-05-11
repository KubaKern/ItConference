package com.itconference.itconference.repository;

import com.itconference.itconference.entity.Lecture;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LectureRepository extends JpaRepository<Lecture,Long> {
    List<Lecture> findByLecturer(String lecturer);

}
