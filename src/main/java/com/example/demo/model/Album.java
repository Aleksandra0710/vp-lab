package com.example.demo.model;

import lombok.Data;

@Data
public class Album {

    Long id;
    String name;
    String genre;
    String releaseYear;

    public Album(Long id, String name, String genre, String releaseYear) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.releaseYear = releaseYear;
    }
}