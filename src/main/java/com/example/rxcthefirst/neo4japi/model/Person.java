package com.example.rxcthefirst.neo4japi.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Node
@Getter
@Setter
public class Person {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String email;
    private String password;
    private String firstName;
    private String lastName;

    @Relationship(type = "FRIEND_OF", direction = Relationship.Direction.OUTGOING)
    private Set<Person> friends = new HashSet<>();

    // getters and setters
}