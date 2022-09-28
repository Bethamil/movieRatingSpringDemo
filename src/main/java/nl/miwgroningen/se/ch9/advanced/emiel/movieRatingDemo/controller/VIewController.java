package nl.miwgroningen.se.ch9.advanced.emiel.movieRatingDemo.controller;

import nl.miwgroningen.se.ch9.advanced.emiel.movieRatingDemo.model.Movie;
import nl.miwgroningen.se.ch9.advanced.emiel.movieRatingDemo.model.View;
import nl.miwgroningen.se.ch9.advanced.emiel.movieRatingDemo.repository.MovieRepository;
import nl.miwgroningen.se.ch9.advanced.emiel.movieRatingDemo.repository.ViewRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

/**
 * @author Emiel Bloem
 * <p>
 * Dit doet het programma
 */

@Controller @RequestMapping("/view")
public class VIewController {

    private final MovieRepository movieRepository;
    private final ViewRepository viewRepository;


    public VIewController(MovieRepository movieRepository, ViewRepository viewRepository) {
        this.movieRepository = movieRepository;
        this.viewRepository = viewRepository;
    }

    @GetMapping("/new/{movieId}")
    protected String createNewView (@PathVariable("movieId") long movieId) {
        Optional<Movie> movie= movieRepository.findById(movieId);

        if (movie.isPresent()) {
            View view = new View();
            view.setMovie(movie.get());
            viewRepository.save(view);
        }
        return "redirect:/";
    }
}
