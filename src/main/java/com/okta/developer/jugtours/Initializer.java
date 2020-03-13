package com.okta.developer.jugtours;

import com.okta.developer.jugtours.model.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Collections;
import java.util.stream.Stream;

@Component
class Initializer implements CommandLineRunner {

    private final GroupRepository repository;

//    private final UserRepository userRepository;

    //constructeur
    public Initializer(GroupRepository repository) {
        this.repository = repository;
//        this.userRepository = userRepository;
    }

    @Override
    public void run(String... strings) {
        Stream.of("Denver JUG", "Utah JUG", "Seattle JUG",
                "Richmond JUG").forEach(name ->
                repository.save(new Group(name))
        );

      /*  Stream.of("Toto", "Titi", "Tata",
                "Tutu").forEach(name ->
                repository.save(new User(name))
        );*/

        Group denverjug = repository.findByName("Denver JUG");//récupère le groupe Denver Jug
        Event e = Event.builder().title("Full Stack Reactive")
                .description("Reactive with Spring Boot + React")
                .date(Instant.parse("2018-12-12T18:00:00.000Z"))
                .build();
        denverjug.setEvents(Collections.singleton(e));
        repository.save(denverjug);

        repository.findAll().forEach(System.out::println);
    }
}
