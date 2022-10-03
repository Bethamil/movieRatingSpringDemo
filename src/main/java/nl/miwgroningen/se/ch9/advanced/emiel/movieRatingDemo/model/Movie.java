package nl.miwgroningen.se.ch9.advanced.emiel.movieRatingDemo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * @author Emiel Bloem
 * <p>
 * Dit doet het programma
 */
@Entity @Getter @Setter
public class Movie {

    public String title;

    @Id
    @GeneratedValue
    private Long movieId;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    List<View> views;

    @ManyToMany
    List<Producer> producers;

    public int getNumberOfViews() {
        int count = 0;
        for (View view : views) {
            count++;
        }
        return count;
    }

    public String getProducerDisplayNames() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Producer producer : producers) {
            stringBuilder.append(producer.getDisplayName()).append(", ");
        }
        stringBuilder.deleteCharAt(stringBuilder.length()-2);
        return stringBuilder.toString();
    }

}
