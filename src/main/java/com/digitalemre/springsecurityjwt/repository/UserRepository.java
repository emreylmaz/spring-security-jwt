package com.digitalemre.springsecurityjwt.repository;

import com.digitalemre.springsecurityjwt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional <User> findByUsername(String username);
}
