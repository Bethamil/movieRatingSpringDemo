package nl.miwgroningen.se.ch9.advanced.emiel.movieRatingDemo.controller;

import nl.miwgroningen.se.ch9.advanced.emiel.movieRatingDemo.model.Movie;
import nl.miwgroningen.se.ch9.advanced.emiel.movieRatingDemo.repository.MovieRepository;
import nl.miwgroningen.se.ch9.advanced.emiel.movieRatingDemo.repository.ProducerRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

/**
 * @author Emiel Bloem
 * <p>
 * Dit doet het programma
 */

@Controller
public class MovieController {

    private final MovieRepository movieRepository;
    private final ProducerRepository producerRepository;

    public MovieController(MovieRepository movieRepository, ProducerRepository producerRepository) {
        this.movieRepository = movieRepository;
        this.producerRepository = producerRepository;
    }

    @GetMapping({"/", "movie/overview"})
    protected String showMoviesOverview(Model model) {
        model.addAttribute("allMovies", movieRepository.findAll());
        return "movieOverview";
    }

    @GetMapping("movie/new")
    protected String showMovieForm(Model model) {
        model.addAttribute("movie", new Movie());
        model.addAttribute("allProducers", producerRepository.findAll());
        return "movieForm";
    }

    @PostMapping("/movie/new")
    protected String saveMovie(@ModelAttribute("movie") Movie movie, BindingResult result) {
        if (!result.hasErrors()) {
            movieRepository.save(movie);
        }
        return "redirect:overview";
    }

    @GetMapping("/movie/update/{movieId}")
    protected String updateBookForm(@PathVariable Long movieId, Model model) {
        Optional<Movie> movie = movieRepository.findById(movieId);

        if (movie.isEmpty()) {
            return "redirect:overview";
        }
        model.addAttribute("movie", movie.get());
        model.addAttribute("allProducers", producerRepository.findAll());

        return "movieForm";
    }

    @GetMapping("/movie/delete/{movieId}")
    protected String deleteMovie(@PathVariable Long movieId) {
        movieRepository.deleteById(movieId);
        return "redirect:/";

    }

}
