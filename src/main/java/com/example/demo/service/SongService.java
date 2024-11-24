package com.example.demo.service;

import com.example.demo.model.Artist;
import com.example.demo.model.Song;
import com.example.demo.model.exceptions.AlbumDoesNotExistException;
import com.example.demo.model.exceptions.SongDoesNotExistException;

import java.util.List;




public interface SongService {

    List<Song> listSongs();

    Artist addArtistToSong(Artist artist, Song song);

    Song findByTrackId(Long trackId) throws SongDoesNotExistException;

    Song saveSong(Long songId, String title, String genre, Integer releaseYear, Long albumId, boolean edit) throws SongDoesNotExistException, AlbumDoesNotExistException;

    public void deleteById(Long id) throws SongDoesNotExistException;
}