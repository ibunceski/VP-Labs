package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.model.exceptions.AlbumDoesNotExistException;
import mk.ukim.finki.wp.lab.model.exceptions.SongDoesNotExistException;

import java.util.List;

public interface SongService {

    List<Song> listSongs();

    Artist addArtistToSong(Artist artist, Song song);

    Song findByTrackId(Long trackId) throws SongDoesNotExistException;

    Song saveSong(Long songId, String title, String genre, Integer releaseYear, Long albumId, boolean edit) throws SongDoesNotExistException, AlbumDoesNotExistException;

    public void deleteById(Long id) throws SongDoesNotExistException;
}
