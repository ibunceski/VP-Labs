package mk.ukim.finki.wp.lab.model.exceptions;

public class SongDoesNotExistException extends Exception {

    public SongDoesNotExistException(Long trackId) {
        super(String.format("Song with track ID %d does not exist", trackId));
    }
}
