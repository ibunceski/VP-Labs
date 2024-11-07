package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.dataholder.DataHolder;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SongRepository {

    public List<Song> findAll() {
        return DataHolder.songList;
    }

    public Optional<Song> findByTrackId(String trackId) {
        return DataHolder.songList.stream().filter(i -> i.getTrackId().equals(trackId)).findFirst();
    }

    public Artist addArtistToSong(Artist artist, Song song) {
        DataHolder.songList.remove(song);
        song.addArtist(artist);
        DataHolder.songList.add(song);
        return artist;
    }
}
