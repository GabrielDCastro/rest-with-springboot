package br.com.gabriel.rest_with_springboot.repositories;

import br.com.gabriel.rest_with_springboot.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}