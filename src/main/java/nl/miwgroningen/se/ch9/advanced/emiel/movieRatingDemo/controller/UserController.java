package nl.miwgroningen.se.ch9.advanced.emiel.movieRatingDemo.controller;

import nl.miwgroningen.se.ch9.advanced.emiel.movieRatingDemo.model.MovieUser;
import nl.miwgroningen.se.ch9.advanced.emiel.movieRatingDemo.model.Producer;
import nl.miwgroningen.se.ch9.advanced.emiel.movieRatingDemo.repository.MovieUserRepository;
import nl.miwgroningen.se.ch9.advanced.emiel.movieRatingDemo.service.MovieUserDetailService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
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
public class UserController {

    private final MovieUserRepository movieUserRepository;
    private final MovieUserDetailService movieUserDetailService;

    private final PasswordEncoder passwordEncoder;


    public UserController(MovieUserRepository movieUserRepository, MovieUserDetailService movieUserDetailService, PasswordEncoder passwordEncoder) {
        this.movieUserRepository = movieUserRepository;
        this.movieUserDetailService = movieUserDetailService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/movieUser")
    protected String updatePassword(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        model.addAttribute("currentUser", movieUserRepository.findByUsername(username));

        System.out.println(username);
        if (username.equals("admin")) {
            System.out.println("YES");
            model.addAttribute("allMovieUsers", movieUserRepository.findAll());
            model.addAttribute("newUser", new MovieUser());

        } else {
            model.addAttribute("allMovieUsers", null);
            model.addAttribute("newUser", new MovieUser());

        }
        return "userForm";
    }

    @PostMapping("/movieUser/update")
    protected String updateCurrentUser(@ModelAttribute("currentUser") MovieUser movieUser, BindingResult result) {
        if (!result.hasErrors()) {
            movieUser.setPassword(passwordEncoder.encode(movieUser.getPassword()));
            System.out.println(movieUser.getUserId());
            System.out.println(movieUser.getUsername());
            System.out.println(movieUser.getPassword());
            movieUserRepository.save(movieUser);
        }
        return "redirect:/";
    }

    @GetMapping("movieUser/updateSelectedUser/{userId}")
    protected String updateUsers(@PathVariable Long userId, Model model) {
        Optional<MovieUser> movieUser = movieUserRepository.findById(userId);

        if (movieUser.isEmpty()) {
            return "redirect:overview";
        }
        model.addAttribute("currentUser", movieUser.get());
        model.addAttribute("newUser", new MovieUser());


        return "userForm";
    }

    @PostMapping("/movieUser/new")
    protected String newUser(@ModelAttribute("currentUser") MovieUser movieUser, BindingResult result) {
        if (!result.hasErrors()) {
            movieUser.setPassword(passwordEncoder.encode(movieUser.getPassword()));
            movieUserRepository.save(movieUser);
        }
        return "redirect:/movieUser";
    }

    @GetMapping("/movieUser/delete/{userId}")
    protected String deleteUser(@PathVariable Long userId) {
        movieUserRepository.deleteById(userId);
        return "redirect:/movieUser";

    }
}
