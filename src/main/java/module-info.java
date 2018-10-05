module localmovie.domain {
    exports com.github.rahmnathan.localmovie.domain;
    requires java.persistence;
    requires movie.info.omdb;
    requires jackson.annotations;
}