package com.example.demo.repository;


import com.example.demo.dataholder.DataHolder;
import com.example.demo.model.Artist;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ArtistRepository {

    public List<Artist> findAll(){
        return DataHolder.artistList;
    }

    public Optional<Artist> findById(Long id){
        return DataHolder.artistList.stream().filter(i -> i.getId().equals(id)).findFirst();
    }

}