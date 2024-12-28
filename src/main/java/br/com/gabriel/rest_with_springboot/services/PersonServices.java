package br.com.gabriel.rest_with_springboot.services;

import br.com.gabriel.rest_with_springboot.model.Person;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {
    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonServices.class.getName());


    public Person findById(String id){
        logger.info("Finding one person");
        Person person = new Person();
        person.setFirstName("gabriel");
        person.setLastName("cas");
        person.setAddress("brasilia");
        person.setGender("M");

        return person;
    }
}
