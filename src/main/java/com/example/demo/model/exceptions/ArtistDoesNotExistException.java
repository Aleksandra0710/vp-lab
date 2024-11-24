package com.example.demo.model.exceptions;



public class ArtistDoesNotExistException extends Exception {

    public ArtistDoesNotExistException(Long id) {
        super(String.format("Artist with id %d does not exist", id));
    }
}
