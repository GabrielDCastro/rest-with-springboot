package br.com.gabriel.rest_with_springboot.controllers;

import br.com.gabriel.rest_with_springboot.model.Books;
import br.com.gabriel.rest_with_springboot.records.BooksRecords;
import br.com.gabriel.rest_with_springboot.records.PersonRecords;
import br.com.gabriel.rest_with_springboot.services.BookServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/book/v1")
public class BooksController {

    @Autowired
    private BookServices services;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityModel<BooksRecords>> findById(@PathVariable(value = "id") Long id){
        Books books = services.findById(id);
        BooksRecords records = new BooksRecords(books.getId(), books.getAuthor(), books.getLaunch_date(), books.getPrice(), books.getTitle());
        EntityModel<BooksRecords> entityModel = EntityModel.of(records);
        entityModel.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        entityModel.add(linkTo(methodOn(PersonController.class).findAll()).withRel("all-persons"));
        return ResponseEntity.ok(entityModel);
    }
}
