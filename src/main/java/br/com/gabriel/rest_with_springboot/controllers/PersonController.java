package br.com.gabriel.rest_with_springboot.controllers;

import br.com.gabriel.rest_with_springboot.model.Person;
import br.com.gabriel.rest_with_springboot.records.PersonRecords;
import br.com.gabriel.rest_with_springboot.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/person/v1")
public class PersonController {

    @Autowired
    private PersonServices service;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityModel<PersonRecords>> findById(@PathVariable(value = "id") Long id){
        Person person = service.findById(id);
        PersonRecords personRecord = new PersonRecords(person.getId(), person.getFirstName(), person.getLastName(), person.getAddress(), person.getGender());
        EntityModel<PersonRecords> entityModel = EntityModel.of(personRecord);
        entityModel.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        entityModel.add(linkTo(methodOn(PersonController.class).findAll()).withRel("all-persons"));
        return ResponseEntity.ok(entityModel);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<CollectionModel<EntityModel<PersonRecords>>> findAll(){
        List<Person> persons = service.findAll();
        List<EntityModel<PersonRecords>> records = persons.stream()
                .map(person -> {
           PersonRecords personRecords = new PersonRecords(
                    person.getId(),
                    person.getFirstName(),
                    person.getLastName(),
                    person.getAddress(),
                    person.getGender()
            );
           return EntityModel.of(personRecords,
                   linkTo(methodOn(PersonController.class).findById(person.getId())).withSelfRel());
        }).toList();
        CollectionModel<EntityModel<PersonRecords>> collectionModel = CollectionModel.of(records);
        collectionModel.add(linkTo(methodOn(PersonController.class).findAll()).withSelfRel());
        return ResponseEntity.ok(collectionModel);
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
