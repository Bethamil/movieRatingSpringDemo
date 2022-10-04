package nl.miwgroningen.se.ch9.advanced.emiel.movieRatingDemo;

import nl.miwgroningen.se.ch9.advanced.emiel.movieRatingDemo.model.Movie;
import nl.miwgroningen.se.ch9.advanced.emiel.movieRatingDemo.model.MovieUser;
import nl.miwgroningen.se.ch9.advanced.emiel.movieRatingDemo.model.Producer;
import nl.miwgroningen.se.ch9.advanced.emiel.movieRatingDemo.repository.MovieRepository;
import nl.miwgroningen.se.ch9.advanced.emiel.movieRatingDemo.repository.MovieUserRepository;
import nl.miwgroningen.se.ch9.advanced.emiel.movieRatingDemo.repository.ProducerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Emiel Bloem
 * <p>
 * Dit doet het programma
 */
@SpringBootApplication
public class MovieDemoKickstarter implements CommandLineRunner {

    private final MovieUserRepository movieUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final ProducerRepository producerRepository;
    private final MovieRepository movieRepository;

    public MovieDemoKickstarter(MovieUserRepository movieUserRepository, PasswordEncoder passwordEncoder,
                                ProducerRepository producerRepository, MovieRepository movieRepository) {
        this.movieUserRepository = movieUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.producerRepository = producerRepository;
        this.movieRepository = movieRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (movieUserRepository.findByUsername("admin").isEmpty()) {
            // create default admin
            MovieUser admin = new MovieUser();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("changeThisNow"));
            movieUserRepository.save(admin);


            //create producer
            Producer producer = new Producer();
            producer.setFirstName("Jan");
            producer.setLastName("Smit");
            producerRepository.save(producer);

            //create movie
            Movie movie = new Movie();
            movie.setTitle("Jan the Man");
            movie.getProducers().add(producer);
            movieRepository.save(movie);
        }


    }
}
