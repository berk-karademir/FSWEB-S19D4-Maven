package com.workintech.s19d1.controller;


import com.workintech.s19d1.dto.ActorRequest;
import com.workintech.s19d1.dto.ActorResponse;
import com.workintech.s19d1.dto.MovieRequest;
import com.workintech.s19d1.dto.MovieResponse;
import com.workintech.s19d1.entity.Actor;
import com.workintech.s19d1.entity.Movie;
import com.workintech.s19d1.service.MovieService;
import com.workintech.s19d1.util.ActorConverter;
import com.workintech.s19d1.util.MovieConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")

public class MovieController {

    private MovieService movieService;


    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public List<MovieResponse> getAllFilms() {
        List<Movie> allMovies = movieService.findAll();
        return MovieConverter.convertMovieCollectionToMovieResponseCollection(allMovies);
    }

    @GetMapping("/{id}")
    public MovieResponse getFilmById(@PathVariable("id") Long id) {
        Movie foundMovie = movieService.findById(id);
        return MovieConverter.convertMovieToMovieResponse(foundMovie);
    }

    @PostMapping
    public MovieResponse saveMovieAndActor(@RequestBody MovieRequest movieRequest) {

        Movie movie = movieRequest.getMovie();
        List<Actor> actors = movieRequest.getActors();

        for (Actor actor : actors) {
            movie.addActor(actor);
        }

        Movie savedMovie = movieService.save(movie);
        return MovieConverter.convertMovieResponseBodyToMovieResponse(savedMovie);
    }


    @PutMapping("/{id}")
    public MovieResponse update(@Validated @PathVariable("id") Long id , @RequestBody MovieRequest movieRequest) {

        Movie foundMovieToSave = movieService.findById(id);
        Movie requestedMovie = movieRequest.getMovie();

        foundMovieToSave.setId(requestedMovie.getId());
        foundMovieToSave.setName(requestedMovie.getName());
        foundMovieToSave.setRating(requestedMovie.getRating());
        foundMovieToSave.setDirectorName(requestedMovie.getDirectorName());
        foundMovieToSave.setActors(requestedMovie.getActors());
        foundMovieToSave.setReleaseDate(requestedMovie.getReleaseDate());

        movieService.save(foundMovieToSave);
        return MovieConverter.convertMovieResponseBodyToMovieResponse(foundMovieToSave);
    }

    @DeleteMapping("/{id}")
    public MovieResponse delete(@PathVariable("id") Long id) {
        Movie foundMovie = movieService.findById(id);
        movieService.delete(foundMovie);
        return MovieConverter.convertMovieToMovieResponse(foundMovie);
    }

}
