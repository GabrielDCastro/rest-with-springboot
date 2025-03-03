package br.com.gabriel.rest_with_springboot.records;

import com.fasterxml.jackson.annotation.JsonProperty;

public record BooksRecords (
        @JsonProperty("ID")
        Long id, String author, String launch_date, String price, String tittle){
}
