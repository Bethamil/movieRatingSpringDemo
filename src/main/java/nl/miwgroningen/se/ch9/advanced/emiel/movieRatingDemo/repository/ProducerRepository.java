package nl.miwgroningen.se.ch9.advanced.emiel.movieRatingDemo.repository;

import nl.miwgroningen.se.ch9.advanced.emiel.movieRatingDemo.model.Producer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProducerRepository extends JpaRepository<Producer, Long> {
}
