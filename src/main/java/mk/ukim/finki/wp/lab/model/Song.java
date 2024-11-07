package mk.ukim.finki.wp.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Song {
    String trackId;
    String title;
    String genre;
    Integer releaseYear;
    List<Artist> performers;

    public Song(String trackId, String title, String genre, Integer releaseYear, List<Artist> performers) {
        this.trackId = trackId;
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.performers = new ArrayList<>(performers);
    }

    public void addArtist(Artist artist) {
        performers.add(artist);
    }
}
