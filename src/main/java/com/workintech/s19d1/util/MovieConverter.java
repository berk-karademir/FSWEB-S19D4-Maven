package com.workintech.s19d1.util;

import com.workintech.s19d1.dto.MovieResponse;

import com.workintech.s19d1.entity.Movie;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MovieConverter {


        public static List<MovieResponse> convertMovieCollectionToMovieResponseCollection(Collection<Movie> movies) {

            List<MovieResponse> movieResponses = new ArrayList<>();
            for(Movie movie: movies) {
                movieResponses.add(convertMovieToMovieResponse(movie));
            }
            return movieResponses;
        }



        public static MovieResponse convertMovieToMovieResponse(Movie foundMovie) {

            return new MovieResponse(
                    foundMovie.getId(),
                    foundMovie.getName(),
                    foundMovie.getDirectorName(),
                    foundMovie.getRating(),
                    foundMovie.getActors()
            );
        }



        public static MovieResponse convertMovieResponseBodyToMovieResponse(Movie savedMovie) {

            return convertMovieToMovieResponse(savedMovie);

//        return new MovieResponse(savedMovie.getId(), savedMovie.getName(),
//                savedMovie.getDirectorName(), savedMovie.getRating(), savedMovie.getActors());

        }


}
