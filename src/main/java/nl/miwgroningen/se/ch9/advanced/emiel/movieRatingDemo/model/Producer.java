package nl.miwgroningen.se.ch9.advanced.emiel.movieRatingDemo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;
import java.util.Set;

/**
 * @author Emiel Bloem
 * <p>
 * Dit doet het programma
 */

@Entity @Getter @Setter
public class Producer {
    @Id
    @GeneratedValue
    private Long producerId;

    private String firstName, lastName;

    @ManyToMany(mappedBy = "producers")
    Set<Movie> movies;

    public String getDisplayName() {
        return String.format("%s %s", firstName, lastName);
    }

}
