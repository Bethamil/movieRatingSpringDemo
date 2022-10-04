package nl.miwgroningen.se.ch9.advanced.emiel.movieRatingDemo.configuration;

import nl.miwgroningen.se.ch9.advanced.emiel.movieRatingDemo.service.MovieUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author Emiel Bloem
 * <p>
 * Dit doet het programma
 */

@Configuration
public class MovieDemoSecurityConfiguration {

    private final MovieUserDetailService movieUserDetailService;

    public MovieDemoSecurityConfiguration(MovieUserDetailService movieUserDetailService) {
        this.movieUserDetailService = movieUserDetailService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests((authorize) -> authorize
                        .antMatchers("/css/*", "/webjars/**", "/images/**").permitAll()
                .antMatchers("/", "/movie/overview").permitAll()
                .anyRequest().authenticated()
        ).formLogin()
                .and().logout()
                .logoutSuccessUrl("/");
        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(movieUserDetailService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
}
