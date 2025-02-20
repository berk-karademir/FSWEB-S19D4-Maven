package com.workintech.s19d1.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "movie", schema = "movies_and_actors_schema")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "director_name")
    @NotNull
    private String directorName;

    @Column(name = "rating")
    private double rating;

    @NotNull
    @Column(name = "release_date")
    private LocalDate releaseDate;


    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST},
            fetch = FetchType.EAGER)
    @JoinTable(name = "movie_actor", schema = "movies_and_actors_schema",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id"))

    private List<Actor> actors;


    //helper method that add actors to this class's instance(movie)
    public void addActor(Actor actor) {
        if (actors == null) {
            actors = new ArrayList<>();
        }
            actors.add(actor);
    }

}
