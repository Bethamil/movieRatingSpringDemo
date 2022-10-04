package nl.miwgroningen.se.ch9.advanced.emiel.movieRatingDemo.service;

import nl.miwgroningen.se.ch9.advanced.emiel.movieRatingDemo.repository.MovieUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Emiel Bloem
 * <p>
 * Dit doet het programma
 */

@Service
public class MovieUserDetailService implements UserDetailsService {

    private final MovieUserRepository movieUserRepository;

    public MovieUserDetailService(MovieUserRepository movieUserRepository) {
        this.movieUserRepository = movieUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return movieUserRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException(String.format("User with name %s not found", username))
        );
    }
}
