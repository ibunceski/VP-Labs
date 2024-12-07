package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.exceptions.ArtistDoesNotExistException;
import mk.ukim.finki.wp.lab.repository.jpa.ArtistRepositoryJpa;
import mk.ukim.finki.wp.lab.service.ArtistService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistServiceImplementation implements ArtistService {

    final ArtistRepositoryJpa artistRepository;

    ArtistServiceImplementation(ArtistRepositoryJpa artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public List<Artist> listArtists() {
        return artistRepository.findAll();
    }

    @Override
    public Artist findById(Long id) throws ArtistDoesNotExistException {
        return artistRepository.findById(id).orElseThrow(() -> new ArtistDoesNotExistException(id));
    }
}
