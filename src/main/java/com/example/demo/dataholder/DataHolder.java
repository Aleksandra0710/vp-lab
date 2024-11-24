package com.example.demo.dataholder;

import com.example.demo.model.Album;
import com.example.demo.model.Artist;
import com.example.demo.model.Song;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {

    public static List<Artist> artistList;
    public static List<Song> songList;
    public static List<Album> albums;

    @PostConstruct
    public void init() {
        artistList = new ArrayList<>();
        songList = new ArrayList<>();
        albums = new ArrayList<>();

        Artist artist1 = new Artist(1L, "Tose", "Proeski", "Singer");
        Artist artist2 = new Artist(2L, "Vlatko", "Lozanoski", "Singer");
        Artist artist3 = new Artist(3L, "Lambe", "Alabakoski", "Singer");
        Artist artist4 = new Artist(4L, "Karolina", "Gocheva", "Singer");
        Artist artist5 = new Artist(5L, "Vlatko", "Stefanovski", "Singer");
        artistList.addAll(List.of(artist1, artist2, artist3, artist4, artist5));

        Album album1 = new Album(1L, "Album1", "Rock", "2001");
        Album album2 = new Album(2L, "Album2", "Pop", "2002");
        Album album3 = new Album(3L, "Album3", "Jazz", "2003");
        Album album4 = new Album(4L, "Album4", "Hip Hop", "2004");
        Album album5 = new Album(5L, "Album5", "Folk", "2005");
        albums.addAll(List.of(album1, album2, album3, album4, album5));


        Song song1 = new Song("Nemir", "Pop", 2003, List.of(artist4, artist1), album1);
        Song song2 = new Song("Mesechina", "Pop", 2005, List.of(artist1), album2);
        Song song3 = new Song("More od solzi", "Pop", 2006, List.of(artist3), album3);
        Song song4 = new Song("Bistra voda", "Rock", 2017, List.of(artist5), album4);
        Song song5 = new Song("Kako den jasno e", "Pop", 2012, List.of(artist2), album5);
        songList.addAll(List.of(song1, song2, song3, song4, song5));


    }
}
