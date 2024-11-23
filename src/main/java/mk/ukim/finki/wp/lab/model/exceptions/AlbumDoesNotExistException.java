package mk.ukim.finki.wp.lab.model.exceptions;

public class AlbumDoesNotExistException extends Exception {

    public AlbumDoesNotExistException(Long id) {
        super(String.format("Album with id %d does not exist", id));
    }
}
