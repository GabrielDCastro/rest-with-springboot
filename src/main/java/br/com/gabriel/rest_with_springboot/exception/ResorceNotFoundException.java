package br.com.gabriel.rest_with_springboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResorceNotFoundException extends RuntimeException{

    public ResorceNotFoundException(String ex) {
        super(ex);
    }

}
