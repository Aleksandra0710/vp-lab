package com.example.demo.repository;

import com.example.demo.dataholder.DataHolder;
import com.example.demo.model.Artist;
import com.example.demo.model.Song;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;



@Repository
public class SongRepository {

    public List<Song> findAll() {
        return DataHolder.songList;
    }

    public Optional<Song> findByTrackId(Long songId) {
        return DataHolder.songList.stream().filter(i -> i.getId().equals(songId)).findFirst();
    }

    public Artist addArtistToSong(Artist artist, Song song) {
        DataHolder.songList.remove(song);
        song.addArtist(artist);
        DataHolder.songList.add(song);
        return artist;
    }

    public void deleteSong(Song song) {
        DataHolder.songList.remove(song);
    }

    public Song addSong(Song song) {
        DataHolder.songList.add(song);
        return song;
    }
}