package com.example.demo.web.controller;


        import com.example.demo.model.Artist;
        import com.example.demo.model.Song;
        import com.example.demo.model.exceptions.ArtistDoesNotExistException;
        import com.example.demo.model.exceptions.SongDoesNotExistException;
        import com.example.demo.service.ArtistService;
        import com.example.demo.service.SongService;
        import jakarta.servlet.http.HttpSession;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.PostMapping;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RequestParam;
        import org.thymeleaf.spring6.SpringTemplateEngine;

@Controller
@RequestMapping("songDetails")
public class SongDetailsController {

    final SongService songService;
    final ArtistService artistService;

    public SongDetailsController(SongService songService, ArtistService artistService, SpringTemplateEngine springTemplateEngine) {
        this.songService = songService;
        this.artistService = artistService;
    }

    @GetMapping
    public String getDetails(Model model, HttpSession session) {
        Long songId = (Long) session.getAttribute("songId");
        Song song;

        try {
            song = songService.findByTrackId(songId);
        } catch (SongDoesNotExistException e) {
            throw new RuntimeException(e);
        }


        model.addAttribute("song", song);

        return "songDetails";
    }

    @PostMapping
    public String processSongAndArtist(HttpSession session, @RequestParam Long artistId) {
        session.setAttribute("artistId", artistId);
        Long songId = (Long) session.getAttribute("songId");

        try {
            Artist artist = artistService.findById(artistId);
            Song song = songService.findByTrackId(songId);

            songService.addArtistToSong(artist, song);
        } catch (ArtistDoesNotExistException | SongDoesNotExistException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/songDetails";
    }
}
