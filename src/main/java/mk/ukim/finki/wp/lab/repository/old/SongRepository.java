package mk.ukim.finki.wp.lab.repository.old;

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

    public Optional<Song> findByTrackId(Long songId) {
        return DataHolder.songList.stream().filter(i -> i.getId().equals(songId)).findFirst();
    }

    public Artist addArtistToSong(Artist artist, Song song) {
        DataHolder.songList.remove(song);
        song.addArtist(artist);
        DataHolder.songList.add(song);
        return artist;
    }

    public void deleteSong(Song song) {
        DataHolder.songList.remove(song);
    }

    public Song addSong(Song song) {
        DataHolder.songList.add(song);
        return song;
    }
}
