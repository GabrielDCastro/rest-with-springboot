package br.com.gabriel.rest_with_springboot.controllers;

import br.com.gabriel.rest_with_springboot.model.Books;
import br.com.gabriel.rest_with_springboot.model.Person;
import br.com.gabriel.rest_with_springboot.records.BooksRecords;
import br.com.gabriel.rest_with_springboot.records.PersonRecords;
import br.com.gabriel.rest_with_springboot.services.BookServices;
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
@RequestMapping("/api/book/v1")
public class BooksController {

    @Autowired
    private BookServices services;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityModel<BooksRecords>> findById(@PathVariable(value = "id") Long id){
        Books books = services.findById(id);
        BooksRecords records = new BooksRecords(books.getId(), books.getAuthor(), books.getLaunch_date(), books.getPrice(), books.getTitle());
        EntityModel<BooksRecords> entityModel = EntityModel.of(records);
        entityModel.add(linkTo(methodOn(BooksController.class).findById(id)).withSelfRel());
        entityModel.add(linkTo(methodOn(BooksController.class).findAll()).withRel("all-Books"));
        return ResponseEntity.ok(entityModel);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<CollectionModel<EntityModel<BooksRecords>>> findAll(){
        List<Books> books = services.findAll();
        List<EntityModel<BooksRecords>> records = books.stream()
                .map(book -> {
                    BooksRecords booksRecords = new BooksRecords(
                            book.getId(),
                            book.getAuthor(),
                            book.getLaunch_date(),
                            book.getPrice(),
                            book.getTitle()
                    );
                    return EntityModel.of(booksRecords,
                            linkTo(methodOn(BooksController.class).findById(book.getId())).withSelfRel());
                }).toList();
        CollectionModel<EntityModel<BooksRecords>> collectionModel = CollectionModel.of(records);
        collectionModel.add(linkTo(methodOn(BooksController.class).findAll()).withSelfRel());
        return ResponseEntity.ok(collectionModel);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BooksRecords> create(@RequestBody BooksRecords books){
        Books booksObj = new Books(
                books.id(),
                books.author(),
                books.launch_date(),
                books.price(),
                books.tittle()
        );
        services.create(booksObj);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BooksRecords> update(@RequestBody BooksRecords books){
        Books booksObj = new Books(
                books.id(),
                books.author(),
                books.launch_date(),
                books.price(),
                books.tittle()
        );
        services.update(booksObj);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id){
        services.delete(id);
        return ResponseEntity.noContent().build();
    }
}
