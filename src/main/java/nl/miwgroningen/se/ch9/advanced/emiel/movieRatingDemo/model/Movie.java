package nl.miwgroningen.se.ch9.advanced.emiel.movieRatingDemo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Emiel Bloem
 * <p>
 * Dit doet het programma
 */
@Entity @Getter @Setter
public class Movie {

    public String title, producer;

    @Id
    @GeneratedValue
    private Long movieId;

}
