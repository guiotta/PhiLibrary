package com.otta.library.movie.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.otta.library.movie.entity.Borrow;
import com.otta.library.movie.entity.Movie;
import com.otta.library.movie.entity.Unit;
import com.otta.library.movie.model.MovieInformation;
import com.otta.library.movie.repository.MovieRepository;

@Service
public class MovieService {
    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public MovieInformation saveMovie(MovieInformation movieInformation) {
        Movie movie = new Movie();
        movie.setName(movieInformation.getName());
        movie.setDirector(movieInformation.getDirector());

        int unitsQuantity = movieInformation.getUnits();
        List<Unit> units = new ArrayList<>();
        for (int index = 0;index < unitsQuantity; index++) {
            units.add(new Unit(movie, new ArrayList<Borrow>()));
        }
        movie.setUnits(units);

        movieRepository.save(movie);
        return movieInformation;
    }

}
