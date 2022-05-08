package com.itconference.itconference.repository;

import com.itconference.itconference.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    List<User> findByName(String name);
    List<User> findByEmail(String email);

}
