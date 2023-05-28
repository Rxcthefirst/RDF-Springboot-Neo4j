package com.example.rxcthefirst.neo4japi.service;

import com.example.rxcthefirst.neo4japi.model.Person;
import com.example.rxcthefirst.neo4japi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public List<Person> getAllPersons() {
        Iterable<Person> personIterable = personRepository.findAll();
        List<Person> persons = new ArrayList<>();
        personIterable.forEach(persons::add);
        return persons;
    }

    public void createRelationship() {
        personRepository.createHardcodedRelationship("Hamjam", "Havok");
    }

    public Person upsertPerson(Person person) {
        return personRepository.save(person);
    }

    public Person getPersonByName(String name) {
        return personRepository.findByName(name);
    }

    public Optional<Person> getPersonById(Long id) { return personRepository.findById(id);}
}
