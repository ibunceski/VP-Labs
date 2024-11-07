package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.exceptions.ArtistDoesNotExistException;

import java.util.List;

public interface ArtistService {
    List<Artist> listArtists();

    Artist findById(Long id) throws ArtistDoesNotExistException;
}
