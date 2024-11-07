package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.model.exceptions.SongDoesNotExistException;

import java.util.List;

public interface SongService {

    List<Song> listSongs();

    Artist addArtistToSong(Artist artist, Song song);

    public Song findByTrackId(String trackId) throws SongDoesNotExistException;

}
