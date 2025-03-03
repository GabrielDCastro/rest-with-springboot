package br.com.gabriel.rest_with_springboot.services;

import br.com.gabriel.rest_with_springboot.exception.ResorceNotFoundException;
import br.com.gabriel.rest_with_springboot.model.Books;
import br.com.gabriel.rest_with_springboot.model.Person;
import br.com.gabriel.rest_with_springboot.repositories.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
import java.util.logging.Logger;

@Service
public class BookServices {

    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    BooksRepository bookRepository;

    public Books findById(Long id){
        return bookRepository.findById(id).orElseThrow(() -> new ResorceNotFoundException("Book not found"));
    }

    public List<Books> findAll(){

        logger.info("Finding all people");
        return bookRepository.findAll();
    }

    public Books create(Books book){
        logger.info("Creating new person");
        bookRepository.save(book);
        return book;
    }

    public Books update(Books book){

        Books entity = bookRepository.findById(book.getId()).orElseThrow(() -> new ResourceAccessException("No records found for this ID"));

        entity.setAuthor(book.getAuthor());
        entity.setPrice(book.getPrice());
        entity.setTitle(book.getTitle());
        entity.setLaunch_date(book.getLaunch_date());

        bookRepository.save(book);
        return entity;
    }

    public void delete(Long id){
        logger.info("Deleting one person");

        var entity = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceAccessException("No records found for this ID"));

        bookRepository.delete(entity);
    }

}
