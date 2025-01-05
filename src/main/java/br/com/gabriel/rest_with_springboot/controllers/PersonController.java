package br.com.gabriel.rest_with_springboot.controllers;

import br.com.gabriel.rest_with_springboot.model.Person;
import br.com.gabriel.rest_with_springboot.records.PersonRecords;
import br.com.gabriel.rest_with_springboot.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonServices service;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonRecords> findById(@PathVariable(value = "id") Long id){
        Person person = service.findById(id);
        return ResponseEntity.ok().body(new PersonRecords(person.getId(), person.getFirstName(), person.getLastName(), person.getAddress(), person.getGender()));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PersonRecords>> findAll(){
        List<Person> persons = service.findAll();
        List<PersonRecords> dtos = persons.stream().map(person -> new PersonRecords(
                person.getId(),
                person.getFirstName(),
                person.getLastName(),
                person.getAddress(),
                person.getGender()
        )).toList();
        return ResponseEntity.ok(dtos);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonRecords> create(@RequestBody PersonRecords person){
        Person personObj = new Person(
                person.id(),
                person.firstName(),
                person.lastName(),
                person.address(),
                person.gender()
        );
        service.create(personObj);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonRecords> update(@RequestBody PersonRecords person){
        Person personObj = new Person(
                person.id(),
                person.firstName(),
                person.lastName(),
                person.address(),
                person.gender()
        );
        service.update(personObj);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
