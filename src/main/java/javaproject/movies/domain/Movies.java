package javaproject.movies.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "movies")
public class Movies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer movieId;

    private String title;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    private String description;
    private Integer duration;
    private String director;

    @Column(name = "average_rating")
    private BigDecimal averageRating;

    @Column(name = "poster_url")
    private String posterUrl;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @ManyToOne
    @JoinColumn(name = "actor_id")
    private Actor actor;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
