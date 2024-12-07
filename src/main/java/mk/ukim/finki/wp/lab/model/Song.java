package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Song {
    @Id
    @GeneratedValue
    Long id;
    String title;
    String genre;
    Integer releaseYear;

    @ManyToMany
    List<Artist> performers;

    @ElementCollection
    List<Integer> ratings;

    @ManyToOne
    private Album album;

    public Song(String title, String genre, Integer releaseYear, List<Artist> performers, Album album) {
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.performers = new ArrayList<>(performers);
        this.ratings = new ArrayList<>();
        this.album = album;
    }

    public Song(String title, String genre, Integer releaseYear, List<Artist> performers, Album album, Long id) {
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.performers = new ArrayList<>(performers);
        this.ratings = new ArrayList<>();
        this.album = album;
        this.id = id;
    }

    public void addArtist(Artist artist) {
        performers.add(artist);
    }

    public void addRating(Integer rating) {
        this.ratings.add(rating);
    }

    public double getAverage() {
        return this.ratings.stream().mapToInt(i -> i).average().orElse(0);
    }
}
