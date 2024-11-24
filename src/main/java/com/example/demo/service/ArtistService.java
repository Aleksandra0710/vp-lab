package com.example.demo.service;



import com.example.demo.model.Artist;
import com.example.demo.model.exceptions.ArtistDoesNotExistException;

import java.util.List;

public interface ArtistService {
    List<Artist> listArtists();

    Artist findById(Long id) throws ArtistDoesNotExistException;
}
