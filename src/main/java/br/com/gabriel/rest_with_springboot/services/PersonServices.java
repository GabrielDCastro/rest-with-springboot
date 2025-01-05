package br.com.gabriel.rest_with_springboot.services;

import br.com.gabriel.rest_with_springboot.model.Person;
import br.com.gabriel.rest_with_springboot.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {
    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    public Person findById(Long id){
        logger.info("Finding one person");
        return repository.findById(id)
            .orElseThrow(() -> new ResourceAccessException("No records found for this ID"));
    }

    public List<Person> findAll(){

        logger.info("Finding all people");
        return repository.findAll();
    }

    public void create(Person person){
        logger.info("Creating new person");
        repository.save(person);
    }

    public void update(Person person){

        Person entity = repository.findById(person.getId()).orElseThrow(() -> new ResourceAccessException("No records found for this ID"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        repository.save(person);
    }

    public void delete(Long id){
        logger.info("Deleting one person");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceAccessException("No records found for this ID"));

        repository.delete(entity);
    }

}
