package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.model.exceptions.AlbumDoesNotExistException;
import mk.ukim.finki.wp.lab.model.exceptions.ArtistDoesNotExistException;
import mk.ukim.finki.wp.lab.model.exceptions.SongDoesNotExistException;
import mk.ukim.finki.wp.lab.repository.jpa.AlbumRepositoryJpa;
import mk.ukim.finki.wp.lab.repository.jpa.ArtistRepositoryJpa;
import mk.ukim.finki.wp.lab.repository.jpa.SongRepositoryJpa;
import mk.ukim.finki.wp.lab.service.SongService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SongServiceImplementation implements SongService {

    final SongRepositoryJpa songRepository;
    final ArtistRepositoryJpa artistRepository;
    final AlbumRepositoryJpa albumRepository;

    public SongServiceImplementation(SongRepositoryJpa songRepositoryJpa, ArtistRepositoryJpa artistRepository, AlbumRepositoryJpa albumRepositoryJpa) {
        this.songRepository = songRepositoryJpa;
        this.artistRepository = artistRepository;
        this.albumRepository = albumRepositoryJpa;
    }

    @Override
    public List<Song> listSongs() {
        return songRepository.findAll();
    }

    @Override
    public Optional<Song> addArtistToSong(Long artistId, Long songId) throws SongDoesNotExistException, ArtistDoesNotExistException {
        Song song = songRepository.findById(songId).orElseThrow(() -> new SongDoesNotExistException(songId));
        Artist artist = artistRepository.findById(artistId).orElseThrow(() -> new ArtistDoesNotExistException(artistId));

        if (song.getPerformers().contains(artist)) return Optional.empty();

        song.addArtist(artist);
        return Optional.of(this.songRepository.save(song));
    }

    @Override
    public Song findByTrackId(Long trackId) throws SongDoesNotExistException {
        return songRepository.findById(trackId).orElseThrow(() -> new SongDoesNotExistException(trackId));
    }

    @Override
    public Optional<Song> saveSong(String title, String genre, Integer releaseYear, Long albumId) throws SongDoesNotExistException, AlbumDoesNotExistException {
        Album album = albumRepository.findById(albumId).orElseThrow(() -> new AlbumDoesNotExistException(albumId));
        Song newSong = new Song(title, genre, releaseYear, new ArrayList<>(), album);

        return Optional.of(this.songRepository.save(newSong));
    }

    @Override
    public Optional<Song> editSong(Long songId, String title, String genre, Integer releaseYear, Long albumId) throws SongDoesNotExistException, AlbumDoesNotExistException {
        Song song = findByTrackId(songId);
        Album album = albumRepository.findById(albumId).orElseThrow(() -> new AlbumDoesNotExistException(albumId));
        song.setTitle(title);
        song.setGenre(genre);
        song.setReleaseYear(releaseYear);
        song.setAlbum(album);

        return Optional.of(this.songRepository.save(song));
    }

    public List<Song> findByAlbumId(Long albumId){
        return songRepository.findAllByAlbum_Id(albumId);
    }

    public void deleteById(Long id) throws SongDoesNotExistException {
        this.songRepository.deleteById(id);
    }


}
