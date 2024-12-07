package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.model.exceptions.AlbumDoesNotExistException;
import mk.ukim.finki.wp.lab.model.exceptions.ArtistDoesNotExistException;
import mk.ukim.finki.wp.lab.model.exceptions.SongDoesNotExistException;

import java.util.List;
import java.util.Optional;

public interface SongService {

    List<Song> listSongs();

    Optional<Song> addArtistToSong(Long artistId, Long songId) throws SongDoesNotExistException, ArtistDoesNotExistException;

    Song findByTrackId(Long trackId) throws SongDoesNotExistException;

    Optional<Song> saveSong(String title, String genre, Integer releaseYear, Long albumId) throws SongDoesNotExistException, AlbumDoesNotExistException;

    Optional<Song> editSong(Long songId, String title, String genre, Integer releaseYear, Long albumId) throws SongDoesNotExistException, AlbumDoesNotExistException;

    public List<Song> findByAlbumId(Long albumId);

    public void deleteById(Long id) throws SongDoesNotExistException;
}
