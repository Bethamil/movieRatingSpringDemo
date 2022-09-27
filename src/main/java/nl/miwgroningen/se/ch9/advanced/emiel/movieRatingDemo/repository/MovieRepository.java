package nl.miwgroningen.se.ch9.advanced.emiel.movieRatingDemo.repository;

import nl.miwgroningen.se.ch9.advanced.emiel.movieRatingDemo.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Emiel Bloem
 * <p>
 * Dit doet het programma
 */
public interface MovieRepository extends JpaRepository<Movie, Long> {
    Optional<Movie> findByTitle(String title);
}
