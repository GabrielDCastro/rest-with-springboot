package br.com.gabriel.rest_with_springboot.services;

import br.com.gabriel.rest_with_springboot.exception.ResorceNotFoundException;
import br.com.gabriel.rest_with_springboot.model.Books;
import br.com.gabriel.rest_with_springboot.repositories.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServices {

    @Autowired
    BooksRepository bookRepository;

    public Books findById(Long id){
        return bookRepository.findById(id).orElseThrow(() -> new ResorceNotFoundException("Book not found"));
    }
}
