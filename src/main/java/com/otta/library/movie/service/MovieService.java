package com.otta.library.movie.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.otta.library.movie.entity.Borrow;
import com.otta.library.movie.entity.Movie;
import com.otta.library.movie.entity.Unit;
import com.otta.library.movie.model.MovieInformation;
import com.otta.library.movie.model.MovieSearchInformation;
import com.otta.library.movie.model.MovieShow;
import com.otta.library.movie.model.pagination.MovieShowPage;
import com.otta.library.movie.repository.MovieRepository;
import com.otta.library.movie.repository.UnitRepository;

@Service
public class MovieService {
    private static final int DEFAULT_ELEMENTS_QUANTITY = 10;
    private static final int CHANGE_PAGE_OFFSET = 1;

    private final MovieRepository movieRepository;
    private final UnitRepository unitRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository, UnitRepository unitRepository) {
        this.movieRepository = movieRepository;
        this.unitRepository = unitRepository;
    }

    public MovieInformation saveMovie(MovieInformation movieInformation) {
        Movie movie = new Movie();
        movie.setName(movieInformation.getName());
        movie.setDirector(movieInformation.getDirector());

        int unitsQuantity = movieInformation.getUnits();
        List<Unit> units = new ArrayList<>();
        for (int index = 0; index < unitsQuantity; index++) {
            units.add(new Unit(movie, new ArrayList<Borrow>()));
        }
        movie.setUnits(units);

        movieRepository.save(movie);
        return movieInformation;
    }

    public MovieShowPage listMovies(int currentPage) {
        Page<Movie> moviesPage = movieRepository.findAll(PageRequest.of(currentPage, DEFAULT_ELEMENTS_QUANTITY));
        Collection<MovieShow> movieShowCollection = new ArrayList<>();
        for (Movie movie : moviesPage) {
            // TODO: Componentizar as linhas abaixo.
            Collection<Unit> unitsByMovie = unitRepository.findByMovie(movie);
            Collection<Unit> rentedUnitsByMovie = unitRepository.findByMovieAndRented(movie);
            Collection<Long> unitsId = unitsByMovie.stream()
                    .filter(unitToFilter -> !rentedUnitsByMovie.contains(unitToFilter))
                    .map(unit -> unit.getId())
                    .collect(Collectors.toList());

            movieShowCollection.add(new MovieShow(movie.getId(), movie.getName(), movie.getDirector(), unitsId));
        }
        String next = moviesPage.hasNext() ? "http://localhost:8080/api/movie/" + (currentPage + CHANGE_PAGE_OFFSET) : null;
        String previous = moviesPage.hasPrevious() ? "http://localhost:8080/api/movie/" + (currentPage - CHANGE_PAGE_OFFSET) : null;

        return new MovieShowPage(currentPage, moviesPage.getTotalPages(), moviesPage.getNumberOfElements(), movieShowCollection, next, previous);
    }

    public MovieShowPage listMovies(MovieSearchInformation movieSearchInformation, int currentPage) {
        Page<Movie> moviesPage = movieRepository.findByName(movieSearchInformation.getMovieName(), PageRequest.of(currentPage, DEFAULT_ELEMENTS_QUANTITY));
        Collection<MovieShow> movieShowCollection = new ArrayList<>();
        for (Movie movie : moviesPage) {
            // TODO: Componentizar as linhas abaixo.
            Collection<Unit> unitsByMovie = unitRepository.findByMovie(movie);
            Collection<Unit> rentedUnitsByMovie = unitRepository.findByMovieAndRented(movie);
            Collection<Long> unitsId = unitsByMovie.stream()
                    .filter(unitToFilter -> !rentedUnitsByMovie.contains(unitToFilter))
                    .map(unit -> unit.getId())
                    .collect(Collectors.toList());

            movieShowCollection.add(new MovieShow(movie.getId(), movie.getName(), movie.getDirector(), unitsId));
        }
        String next = moviesPage.hasNext() ? "http://localhost:8080/api/movie/" + (currentPage + CHANGE_PAGE_OFFSET) : null;
        String previous = moviesPage.hasPrevious() ? "http://localhost:8080/api/movie/" + (currentPage - CHANGE_PAGE_OFFSET) : null;

        return new MovieShowPage(currentPage, moviesPage.getTotalPages(), moviesPage.getNumberOfElements(), movieShowCollection, next, previous);
    }

}
