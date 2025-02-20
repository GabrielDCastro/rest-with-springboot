package br.com.gabriel.rest_with_springboot.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "books")
public class Books implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "author")
    private String author;
    @Column(name = "longtext", nullable = false)
    private String longtext;
    @Column(name = "launch_date", nullable = false)
    private String launch_date;
    @Column(name = "datetime", nullable = false)
    private String datetime;
    @Column(name = "price", nullable = false)
    private String price;
    @Column(name = "title", nullable = false)
    private String title;

    public Books(Long id) {
        this.id = id;
    }

    public Books(Long id, String author, String longtext, String launch_date, String datetime, String price, String title) {
        this.id = id;
        this.author = author;
        this.longtext = longtext;
        this.launch_date = launch_date;
        this.datetime = datetime;
        this.price = price;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLongtext() {
        return longtext;
    }

    public void setLongtext(String longtext) {
        this.longtext = longtext;
    }

    public String getLaunch_date() {
        return launch_date;
    }

    public void setLaunch_date(String launch_date) {
        this.launch_date = launch_date;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
