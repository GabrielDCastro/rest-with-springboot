package br.com.gabriel.rest_with_springboot.repositories;

import br.com.gabriel.rest_with_springboot.model.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BooksRepository extends JpaRepository<Books, Long> {
}
