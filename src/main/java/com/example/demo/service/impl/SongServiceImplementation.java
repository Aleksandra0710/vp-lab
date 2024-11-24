package com.example.demo.service.impl;

import com.example.demo.model.Album;
import com.example.demo.model.Artist;
import com.example.demo.model.Song;
import com.example.demo.model.exceptions.AlbumDoesNotExistException;
import com.example.demo.model.exceptions.SongDoesNotExistException;
import com.example.demo.repository.AlbumRepository;
import com.example.demo.repository.ArtistRepository;
import com.example.demo.repository.SongRepository;
import com.example.demo.service.SongService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;



@Service
public class SongServiceImplementation implements SongService {

    final SongRepository songRepository;
    final ArtistRepository artistRepository;
    final AlbumRepository albumRepository;

    public SongServiceImplementation(SongRepository songRepository, ArtistRepository artistRepository, AlbumRepository albumRepository) {
        this.songRepository = songRepository;
        this.artistRepository = artistRepository;
        this.albumRepository = albumRepository;
    }

    @Override
    public List<Song> listSongs() {
        return songRepository.findAll();
    }

    @Override
    public Artist addArtistToSong(Artist artist, Song song) {
        if (song.getPerformers().contains(artist)) return artist;
        return songRepository.addArtistToSong(artist, song);
    }

    @Override
    public Song findByTrackId(Long trackId) throws SongDoesNotExistException {
        return songRepository.findByTrackId(trackId).orElseThrow(() -> new SongDoesNotExistException(trackId));
    }

    @Override
    public Song saveSong(Long songId, String title, String genre, Integer releaseYear, Long albumId, boolean edit) throws SongDoesNotExistException, AlbumDoesNotExistException {
        Song newSong;
        if (edit) {
            Song song = findByTrackId(songId);
            List<Artist> artists = song.getPerformers();
            Album album = albumRepository.findById(albumId).orElseThrow(() -> new AlbumDoesNotExistException(albumId));
            songRepository.deleteSong(song);
            newSong = new Song(title, genre, releaseYear, artists, album, songId);
        } else {
            Album album = albumRepository.findById(albumId).orElseThrow(() -> new AlbumDoesNotExistException(albumId));
            newSong = new Song(title, genre, releaseYear, new ArrayList<>(), album);
        }
        return songRepository.addSong(newSong);
    }

    public void deleteById(Long id) throws SongDoesNotExistException {
        this.songRepository.deleteSong(songRepository.findByTrackId(id).orElseThrow(() -> new SongDoesNotExistException(id)));
    }


}

