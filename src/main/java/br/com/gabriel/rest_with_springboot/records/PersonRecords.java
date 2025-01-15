package br.com.gabriel.rest_with_springboot.records;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.hateoas.EntityModel;

@JsonPropertyOrder({"id", "firstName", "lastName", "address", "gender"})
public record PersonRecords (
        @JsonProperty("ID")
        Long id, String firstName, String lastName, String address, String gender) {
}
