package com.ust.UST_Projects.controller;

import com.ust.UST_Projects.model.Person;
import com.ust.UST_Projects.repository.Personrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private Personrepo repo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public String registerperson(@RequestBody Person person){
        //person.setRole(PersonConstant.DEFAULT_ROLE);
        String encodedPassword=passwordEncoder.encode(person.getPassword());
        person.setPassword(encodedPassword);
        repo.save(person);
        return "hi"+person.getUsername()+"registered";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/getallpersons")
    public List<Person> getAllPersons(){
        return repo.findAll();
    }





}
