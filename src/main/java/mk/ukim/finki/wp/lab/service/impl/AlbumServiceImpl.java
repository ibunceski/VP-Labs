package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.repository.jpa.AlbumRepositoryJpa;
import mk.ukim.finki.wp.lab.service.AlbumService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepositoryJpa albumRepositoryJpa;

    public AlbumServiceImpl(AlbumRepositoryJpa albumRepositoryJpa) {
        this.albumRepositoryJpa = albumRepositoryJpa;
    }


    @Override
    public List<Album> findAll() {
        return albumRepositoryJpa.findAll();
    }
}
