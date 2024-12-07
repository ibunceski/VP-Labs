package mk.ukim.finki.wp.lab.web.controller;

import jakarta.servlet.http.HttpSession;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.model.exceptions.ArtistDoesNotExistException;
import mk.ukim.finki.wp.lab.model.exceptions.SongDoesNotExistException;
import mk.ukim.finki.wp.lab.service.ArtistService;
import mk.ukim.finki.wp.lab.service.SongService;
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
            songService.addArtistToSong(artistId, songId);
        } catch (ArtistDoesNotExistException | SongDoesNotExistException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/songDetails";
    }
}
