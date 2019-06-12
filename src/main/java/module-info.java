module localmovie.domain {
    exports com.github.rahmnathan.localmovie.domain;
    requires java.persistence;
    requires movie.info.omdb;
    requires com.fasterxml.jackson.annotation;
}