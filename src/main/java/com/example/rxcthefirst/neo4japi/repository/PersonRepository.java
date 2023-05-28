package com.example.rxcthefirst.neo4japi.repository;

import com.example.rxcthefirst.neo4japi.model.Person;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends Neo4jRepository<Person, Long> {

    @Query("MATCH (a:Person {name: $name1}), (b:Person {name: $name2}) MERGE (a)-[:KNOWS]->(b)")
    void createHardcodedRelationship(String name1, String name2);

    Person findByName(String name);

    List<Person> findAll();

}