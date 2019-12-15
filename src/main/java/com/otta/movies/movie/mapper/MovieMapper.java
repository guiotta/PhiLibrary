package com.otta.movies.movie.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.otta.movies.movie.entity.Borrow;
import com.otta.movies.movie.entity.Movie;
import com.otta.movies.movie.entity.Unit;
import com.otta.movies.movie.model.MovieInformation;

/**
 * Classe para mapear um {@link MovieInformation} em um {@link Movie}.
 * @author Guilherme
 *
 */
@Component
public class MovieMapper {

    public Movie map(MovieInformation movieInformation) {
        Movie movie = new Movie();
        movie.setName(movieInformation.getName());
        movie.setDirector(movieInformation.getDirector());

        int unitsQuantity = movieInformation.getUnits();
        List<Unit> units = new ArrayList<>();
        for (int index = 0; index < unitsQuantity; index++) {
            units.add(new Unit(movie, new ArrayList<Borrow>()));
        }
        movie.setUnits(units);

        return movie;
    }
}
