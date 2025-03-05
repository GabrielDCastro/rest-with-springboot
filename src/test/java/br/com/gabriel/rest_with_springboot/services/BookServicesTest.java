package br.com.gabriel.rest_with_springboot.services;

import br.com.gabriel.rest_with_springboot.model.Books;
import br.com.gabriel.rest_with_springboot.repositories.BooksRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookServicesTest {

    @Mock
    private BooksRepository repository;

    @InjectMocks
    private BookServices service;

    private Books mockBooks;

    @BeforeEach
    void setUp() {
        mockBooks = new Books(1L, "Carl Sagan", "12/10/1995", "45.00", "O mundo assombrado pelos demônios");
    }

    @Test
    void findById() {
        when(repository.findById(1L)).thenReturn(Optional.of(mockBooks));
        Books result = service.findById(1L);
        assertNotNull(result);
        assertEquals(mockBooks.getId(), result.getId());
    }

    @Test
    void shouldThrowError_NotFoundID(){
        when(repository.findById(1L)).thenReturn(Optional.empty());
        Exception exception = assertThrows(RuntimeException.class, () -> service.findById(1L));
        assertEquals("Book not found", exception.getMessage());
    }

    @Test
    void findAll() {
        List<Books> books = List.of(mockBooks, new Books(2L, "Miasaki", "12/10/1989", "45.00", "Berserk"));
        when(repository.findAll()).thenReturn(books);
        List<Books> result = service.findAll();
        assertEquals(2, result.size());
    }

    @Test
    void create() {
        when(repository.save(any(Books.class))).thenReturn(mockBooks);
        Books result = service.create(mockBooks);
        assertNotNull(result);
        assertNotNull("Carl Sagan", result.getAuthor());
    }

    @Test
    void update() {
        when(repository.findById(1L)).thenReturn(Optional.of(mockBooks));
        when(repository.save(any(Books.class))).thenReturn(mockBooks);
        Books updateBook = new Books(1L, "Update", "", "", "");
        Books result = service.update(updateBook);
        assertNotNull(result);
        assertEquals("Update", result.getAuthor());
    }

    @Test
    void delete() {
        when(repository.findById(1L)).thenReturn(Optional.of(mockBooks));
        doNothing().when(repository).delete(mockBooks);
        assertDoesNotThrow(() -> service.delete(1L));
    }
}