package com.example.demo.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Song {
    String title;
    String genre;
    Integer releaseYear;
    List<Artist> performers;
    List<Integer> ratings;
    Long id;
    private Album album;

    static Long currentId = 1L;

    public Song(String title, String genre, Integer releaseYear, List<Artist> performers, Album album) {
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.performers = new ArrayList<>(performers);
        this.ratings = new ArrayList<>();
        this.album = album;
        this.id = currentId++;
    }

    public Song(String title, String genre, Integer releaseYear, List<Artist> performers, Album album, Long id) {
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.performers = new ArrayList<>(performers);
        this.ratings = new ArrayList<>();
        this.album = album;
        this.id = id;
    }

    public void addArtist(Artist artist) {
        performers.add(artist);
    }

    public void addRating(Integer rating) {
        this.ratings.add(rating);
    }

    public double getAverage() {
        return this.ratings.stream().mapToInt(i -> i).average().orElse(0);
    }
}
