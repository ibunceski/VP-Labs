package mk.ukim.finki.wp.lab.model.exceptions;

public class SongDoesNotExistException extends Exception {

    public SongDoesNotExistException(String trackId) {
        super(String.format("Song with track ID %s does not exist", trackId));
    }
}
