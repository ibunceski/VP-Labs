package mk.ukim.finki.wp.lab.model.exceptions;

public class ArtistDoesNotExistException extends Exception {

    public ArtistDoesNotExistException(Long id) {
        super(String.format("Artist with id %d does not exist", id));
    }
}
