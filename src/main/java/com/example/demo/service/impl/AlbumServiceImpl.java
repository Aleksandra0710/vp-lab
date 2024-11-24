package com.example.demo.service.impl;

import com.example.demo.model.Album;
import com.example.demo.repository.AlbumRepository;
import com.example.demo.service.AlbumService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;

    public AlbumServiceImpl(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }


    @Override
    public List<Album> findAll() {
        return albumRepository.findAll();
    }
}
