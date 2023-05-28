package com.example.rxcthefirst.neo4japi.controller;

import com.example.rxcthefirst.neo4japi.model.Person;
import com.example.rxcthefirst.neo4japi.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/persons")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping("/all")
    public ResponseEntity<List<Person>> getAllPersons() {
        List<Person> persons = personService.getAllPersons();
        return new ResponseEntity<>(persons, HttpStatus.OK);
    }

    @PostMapping("/relate")
    public ResponseEntity<Person> createAndRelate(@RequestBody Person person) {
        Person hamjam = personService.getPersonByName("Hamjam");
        hamjam.getFriends().add(person);
        Person personToCreate = personService.upsertPerson(person);
        personService.upsertPerson(hamjam);

        return new ResponseEntity<>(hamjam, HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<Person> upsertPerson(@RequestBody Person person) {
        Person upsertedPerson = personService.upsertPerson(person);

        return new ResponseEntity<>(upsertedPerson, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonByName(@PathVariable Long id) {
        Optional<Person> personOpt = personService.getPersonById(id);
        Person person = personOpt.get();
        if (person == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(person, HttpStatus.OK);
        }
    }

    @GetMapping("/test")
    public void createHardcodedRelationship() {
        personService.createRelationship();
    }
}
