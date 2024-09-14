package com.ust.UST_Projects.repository;

import com.ust.UST_Projects.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Personrepo extends JpaRepository<Person,Integer>{
    Optional<Person> findByUsername(String username);
}
