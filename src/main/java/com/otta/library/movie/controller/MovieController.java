package com.otta.library.movie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.otta.library.movie.model.MovieInformation;
import com.otta.library.movie.model.MovieSearchInformation;
import com.otta.library.movie.model.MovieShow;
import com.otta.library.movie.service.MovieService;
import com.otta.library.pagination.Page;
import com.otta.library.pagination.model.PageEndpoint;

/**
 * {@link RestController} para as operações HTTP dos Filmes.
 * @author Guilherme
 *
 */
@RestController
@RequestMapping("/api/movie")
public class MovieController {
    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping(produces = "application/json")
    public @ResponseBody ResponseEntity<Page<MovieShow>> returnMovies() {
        return ResponseEntity.ok(movieService.listMovies(PageEndpoint.MOVIE, 0));
    }

    @GetMapping(path = "/{page}", produces = "application/json")
    public @ResponseBody ResponseEntity<Page<MovieShow>> returnMovies(@PathVariable(name = "page") int currentPage) {
        return ResponseEntity.ok(movieService.listMovies(PageEndpoint.MOVIE, currentPage));
    }

    @PostMapping(path = "/search", consumes = "application/json", produces = "application/json")
    public @ResponseBody ResponseEntity<Page<MovieShow>> returnMovies(@RequestBody MovieSearchInformation movieSearchInformation) {
        return ResponseEntity.ok(movieService.listMovies(PageEndpoint.SEARCH, movieSearchInformation, 0));
    }

    @PostMapping(path = "/search/{page}", consumes = "application/json", produces = "application/json")
    public @ResponseBody ResponseEntity<Page<MovieShow>> returnMovies(
            @RequestBody MovieSearchInformation movieSearchInformation, @PathVariable(name = "page") int currentPage) {
        return ResponseEntity.ok(movieService.listMovies(PageEndpoint.SEARCH, movieSearchInformation, currentPage));
    }

    @PostMapping(consumes = "application/json")
    public @ResponseBody ResponseEntity<MovieInformation> saveMovie(@RequestBody MovieInformation movieInformation) {
        return ResponseEntity.ok(movieService.saveMovie(movieInformation));
    }
}
