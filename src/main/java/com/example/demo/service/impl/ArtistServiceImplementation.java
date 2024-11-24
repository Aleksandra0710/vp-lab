package com.example.demo.service.impl;

import com.example.demo.model.Artist;
import com.example.demo.model.exceptions.ArtistDoesNotExistException;
import com.example.demo.repository.ArtistRepository;
import com.example.demo.service.ArtistService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistServiceImplementation implements ArtistService {

    final ArtistRepository artistRepository;

    ArtistServiceImplementation(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public List<Artist> listArtists() {
        return artistRepository.findAll();
    }

    @Override
    public Artist findById(Long id) throws ArtistDoesNotExistException {
        return artistRepository.findById(id).orElseThrow(() -> new ArtistDoesNotExistException(id));
    }
}
