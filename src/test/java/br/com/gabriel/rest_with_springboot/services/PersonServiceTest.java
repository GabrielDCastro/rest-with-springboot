package br.com.gabriel.rest_with_springboot.services;

import br.com.gabriel.rest_with_springboot.model.Person;
import br.com.gabriel.rest_with_springboot.repositories.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    @Mock
    private PersonRepository repository;

    @InjectMocks
    private PersonServices service;

    private Person mockPerson;

    @BeforeEach
    void setup() {
        mockPerson = new Person(1L, "John", "Doe", "123 Street", "Male");
    }

    @Test
    void testFindbyId_success(){
        when(repository.findById(1L)).thenReturn(Optional.of(mockPerson));
        Person result = service.findById(1L);
        assertNotNull(result);
        assertEquals(mockPerson.getId(), result.getId());
    }

    @Test
    void testFindById_NotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> service.findById(1L));

        assertEquals("No records found for this ID", exception.getMessage());
    }

    @Test
    void testFindAll() {
        List<Person> persons = List.of(mockPerson, new Person(2L, "Jane", "Doe", "456 Avenue", "Female"));
        when(repository.findAll()).thenReturn(persons);

        List<Person> result = service.findAll();

        assertEquals(2, result.size());
    }

    @Test
    void testCreate() {
        when(repository.save(any(Person.class))).thenReturn(mockPerson);

        Person result = service.create(mockPerson);

        assertNotNull(result);
        assertEquals("John", result.getFirstName());
    }

    @Test
    void testUpdate_Success() {
        when(repository.findById(1L)).thenReturn(Optional.of(mockPerson));
        when(repository.save(any(Person.class))).thenReturn(mockPerson);

        Person updatedPerson = new Person(1L, "Updated", "Doe", "789 Road", "Male");
        Person result = service.update(updatedPerson);

        assertNotNull(result);
        assertEquals("Updated", result.getFirstName());
    }

    @Test
    void testUpdate_NotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        Person updatedPerson = new Person(1L, "Updated", "Doe", "789 Road", "Male");

        Exception exception = assertThrows(RuntimeException.class, () -> service.update(updatedPerson));

        assertEquals("No records found for this ID", exception.getMessage());
    }

    @Test
    void testDelete_Success() {
        when(repository.findById(1L)).thenReturn(Optional.of(mockPerson));
        doNothing().when(repository).delete(mockPerson);

        assertDoesNotThrow(() -> service.delete(1L));
    }

    @Test
    void testDelete_NotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> service.delete(1L));

        assertEquals("No records found for this ID", exception.getMessage());
    }
}