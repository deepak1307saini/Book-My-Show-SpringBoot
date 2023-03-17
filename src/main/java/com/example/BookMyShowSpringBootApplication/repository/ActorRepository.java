package com.example.BookMyShowSpringBootApplication.repository;

import com.example.BookMyShowSpringBootApplication.entity.Actor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ActorRepository extends CrudRepository<Actor, Integer> {
    boolean existsByName(String name);
    Optional<Actor> findByName(String name);

    List<Actor> findByNameContaining(String partialActorName);
}

