package com.example.demo.web;



        import com.example.demo.model.Song;
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

@WebServlet(name = "SongServlet", urlPatterns = "/listSongs")
public class SongListServlet extends HttpServlet {

    final SongService songService;
    final ArtistService artistService;
    final SpringTemplateEngine springTemplateEngine;

    public SongListServlet(SongService songService, ArtistService artistService, SpringTemplateEngine springTemplateEngine) {
        this.songService = songService;
        this.artistService = artistService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext webContext = new WebContext(webExchange);

        webContext.setVariable("songs", songService.listSongs());

        springTemplateEngine.process("listSongs.html", webContext, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long songId = Long.valueOf(req.getParameter("song"));
        try {
            Song song = songService.findByTrackId(songId);
            int rating = Integer.parseInt(req.getParameter("songRating" + songId));
            req.getSession().setAttribute("songId", songId);
            if (rating == 0) {
                resp.sendRedirect("artist");
            } else {
                song.addRating(rating);
                resp.sendRedirect("listSongs");
            }
        } catch (SongDoesNotExistException e) {
            System.out.println("Song does not exist");
        }

    }
}