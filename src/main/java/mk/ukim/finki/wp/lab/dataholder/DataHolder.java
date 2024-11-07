package mk.ukim.finki.wp.lab.dataholder;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {

    public static List<Artist> artistList;
    public static List<Song> songList;

    @PostConstruct
    public void init() {
        artistList = new ArrayList<>();
        songList = new ArrayList<>();

        Artist artist1 = new Artist(1L, "Tose", "Proeski", "Singer");
        Artist artist2 = new Artist(2L, "Vlatko", "Lozanoski", "Singer");
        Artist artist3 = new Artist(3L, "Lambe", "Alabakoski", "Singer");
        Artist artist4 = new Artist(4L, "Karolina", "Gocheva", "Singer");
        Artist artist5 = new Artist(5L, "Vlatko", "Stefanovski", "Singer");
        artistList.addAll(List.of(artist1, artist2, artist3, artist4, artist5));

        songList.add(new Song("1", "Nemir", "Pop", 2003, List.of(artist4, artist1)));
        songList.add(new Song("2", "Mesechina", "Pop", 2005, List.of(artist1)));
        songList.add(new Song("3", "More od solzi", "Pop", 2006, List.of(artist3)));
        songList.add(new Song("4", "Bistra voda", "Rock", 2017, List.of(artist5)));
        songList.add(new Song("5", "Kako den jasno e", "Pop", 2012, List.of(artist2)));
    }
}
