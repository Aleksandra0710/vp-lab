package com.example.demo.repository;


import com.example.demo.dataholder.DataHolder;
import com.example.demo.model.Album;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AlbumRepository {

    public List<Album> findAll() {
        return DataHolder.albums;
    }

    public Optional<Album> findById(Long id) {
        return DataHolder.albums.stream().filter(i -> i.getId().equals(id)).findFirst();
    }
}

