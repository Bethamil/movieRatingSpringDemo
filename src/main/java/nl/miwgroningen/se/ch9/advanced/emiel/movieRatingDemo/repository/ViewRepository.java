package nl.miwgroningen.se.ch9.advanced.emiel.movieRatingDemo.repository;

import nl.miwgroningen.se.ch9.advanced.emiel.movieRatingDemo.model.View;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Emiel Bloem
 * <p>
 * Dit doet het programma
 */
public interface ViewRepository extends JpaRepository<View, Long> {
}
