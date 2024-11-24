package com.example.demo.web.controller;


        import com.example.demo.service.ArtistService;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.thymeleaf.spring6.SpringTemplateEngine;

@Controller
@RequestMapping("/artist")
public class ArtistController {

    final ArtistService artistService;
    final SpringTemplateEngine springTemplateEngine;

    public ArtistController(ArtistService artistService, SpringTemplateEngine springTemplateEngine) {
        this.artistService = artistService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @GetMapping
    public String showArtists(Model model) {
        model.addAttribute("artists", artistService.listArtists());
        return "artistsList";
    }
}

