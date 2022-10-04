package nl.miwgroningen.se.ch9.advanced.emiel.movieRatingDemo.repository;

import nl.miwgroningen.se.ch9.advanced.emiel.movieRatingDemo.model.MovieUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Emiel Bloem
 * <p>
 * Dit doet het programma
 */


public interface MovieUserRepository extends JpaRepository<MovieUser, Long> {
    Optional<MovieUser> findByUsername(String username);
}
