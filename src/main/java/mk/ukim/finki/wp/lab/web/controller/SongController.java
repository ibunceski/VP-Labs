package mk.ukim.finki.wp.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.model.exceptions.AlbumDoesNotExistException;
import mk.ukim.finki.wp.lab.model.exceptions.SongDoesNotExistException;
import mk.ukim.finki.wp.lab.service.AlbumService;
import mk.ukim.finki.wp.lab.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/songs")
public class SongController {

    private final SongService songService;
    private final AlbumService albumService;

    public SongController(SongService songService, AlbumService albumService) {
        this.songService = songService;
        this.albumService = albumService;
    }

    @GetMapping
    public String getSongsPage(@RequestParam(required = false) String error, @RequestParam(defaultValue = "-1") Long album, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        model.addAttribute("albums", albumService.findAll());

        if (album == -1) {
            model.addAttribute("songs", songService.listSongs());
        } else {
            model.addAttribute("songs", songService.findByAlbumId(album));
        }


        return "listSongs";
    }

    @PostMapping
    public String processArtists(@RequestParam(required = false) Long songId, HttpServletRequest req) {
        if (songId == null) {
            return "redirect:/songs?error=MissingSongId";
        }

        try {
            Song song = songService.findByTrackId(songId);
            int rating = Integer.parseInt(req.getParameter("songRating" + songId));
            req.getSession().setAttribute("songId", songId);
            if (rating == 0) {
                return "redirect:/artist";
            } else {
                song.addRating(rating);
                return "redirect:/songs";
            }
        } catch (SongDoesNotExistException e) {
            return "redirect:/listSongs?error=SongDoesNotExist";
        }
    }


    @RequestMapping("/add")
    public String saveSong(
            @RequestParam String title,
            @RequestParam String genre,
            @RequestParam Integer releaseYear,
            @RequestParam Long albumId) {
        try {
            this.songService.saveSong(title, genre, releaseYear, albumId);
        } catch (SongDoesNotExistException e) {
            return "redirect:/listSongs?error=SongDoesNotExist";
        } catch (AlbumDoesNotExistException e) {
            return "redirect:/songs?error=AlbumDoesNotExistException";
        }
        return "redirect:/songs";
    }

    @GetMapping("/delete/{id}")
    public String deleteSong(@PathVariable Long id) {
        try {
            this.songService.deleteById(id);
        } catch (SongDoesNotExistException e) {
            return "redirect:/songs?error=SongDoesNotExistException";
        }
        return "redirect:/songs";
    }

    @RequestMapping("/edit/{songId}")
    public String editSong(
            @PathVariable Long songId,
            @RequestParam String title,
            @RequestParam String genre,
            @RequestParam Integer releaseYear,
            @RequestParam Long albumId) {
        try {
            this.songService.editSong(songId, title, genre, releaseYear, albumId);
        } catch (SongDoesNotExistException e) {
            return "redirect:/songs?error=SongDoesNotExistException";
        } catch (AlbumDoesNotExistException e) {
            return "redirect:/songs?error=AlbumDoesNotExistException";
        }
        return "redirect:/songs";
    }

    @GetMapping("/add-form")
    public String getAddSongPage(Model model) {
        List<Album> albums = this.albumService.findAll();
        model.addAttribute("albums", albums);
        return "add-song";
    }

    @GetMapping("/edit-form/{id}")
    public String getEditSongPage(@PathVariable Long id, Model model) {
        Song song;
        try {
            song = this.songService.findByTrackId(id);
        } catch (SongDoesNotExistException e) {
            return "redirect:/songs?error=SongDoesNotExist";
        }
        List<Album> albums = this.albumService.findAll();
        model.addAttribute("albums", albums);
        model.addAttribute("song", song);
        return "add-song";
    }

}
