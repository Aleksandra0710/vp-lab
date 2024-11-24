package com.example.demo.web;

import com.example.demo.model.Artist;
import com.example.demo.model.Song;
import com.example.demo.model.exceptions.ArtistDoesNotExistException;
import com.example.demo.model.exceptions.SongDoesNotExistException;
import com.example.demo.service.ArtistService;
import com.example.demo.service.SongService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "SongDetailsServlet", urlPatterns = "/songDetails")
public class SongDetailsServlet extends HttpServlet {

    final SongService songService;
    final ArtistService artistService;
    final SpringTemplateEngine springTemplateEngine;

    public SongDetailsServlet(SongService songService, ArtistService artistService, SpringTemplateEngine springTemplateEngine) {
        this.songService = songService;
        this.artistService = artistService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long songId = (Long) req.getSession().getAttribute("songId");
        Song song;

        try {
            song = songService.findByTrackId(songId);
        } catch (SongDoesNotExistException e) {
            throw new RuntimeException(e);
        }

        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext webContext = new WebContext(webExchange);

        webContext.setVariable("song", song);

        springTemplateEngine.process("songDetails.html", webContext, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long artistId = Long.valueOf(req.getParameter("artistId"));
        req.getSession().setAttribute("artistId", artistId);
        Long songId = (Long) req.getSession().getAttribute("songId");

        try {
            Artist artist = artistService.findById(artistId);
            Song song = songService.findByTrackId(songId);

            songService.addArtistToSong(artist, song);
        } catch (ArtistDoesNotExistException | SongDoesNotExistException e) {
            throw new RuntimeException(e);
        }
        resp.sendRedirect("/songDetails");
    }
}
