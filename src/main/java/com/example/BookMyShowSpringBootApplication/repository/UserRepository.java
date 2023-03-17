package com.example.BookMyShowSpringBootApplication.repository;

import com.example.BookMyShowSpringBootApplication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    public User findByEmail(String email);

    public Optional<User> findById(Long id);

    public User findByUsername(String username);

    public void deleteById(Long id);
}
