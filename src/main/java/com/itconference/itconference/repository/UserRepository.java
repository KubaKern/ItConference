package com.itconference.itconference.repository;

import com.itconference.itconference.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    List<User> findByName(String name);
    List<User> findByEmail(String email);

}
