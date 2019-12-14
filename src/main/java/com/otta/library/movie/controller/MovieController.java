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

import com.otta.library.movie.model.BorrowReturnInformation;
import com.otta.library.movie.model.MovieBorrow;
import com.otta.library.movie.model.MovieInformation;
import com.otta.library.movie.model.MovieShowPage;
import com.otta.library.movie.model.RentedMovieShowPage;
import com.otta.library.movie.service.BorrowService;
import com.otta.library.movie.service.MovieService;

@RestController
@RequestMapping("/api/movie")
public class MovieController {
    private final MovieService movieService;
    private final  BorrowService borrowService;

    @Autowired
    public MovieController(MovieService movieService, BorrowService borrowService) {
        this.movieService = movieService;
        this.borrowService = borrowService;
    }

    @GetMapping(produces = "application/json")
    public @ResponseBody ResponseEntity<MovieShowPage> returnMovies() {
        return ResponseEntity.ok(movieService.listMovies(0));
    }

    @PostMapping(consumes = "application/json")
    public @ResponseBody ResponseEntity<MovieInformation> saveMovie(@RequestBody MovieInformation movieInformation) {
        return ResponseEntity.ok(movieService.saveMovie(movieInformation));
    }

    @PostMapping(path = "/borrow", consumes = "application/json")
    public @ResponseBody ResponseEntity<MovieBorrow> borrowMovie(@RequestBody MovieBorrow movieBorrow)  {
        return ResponseEntity.ok(borrowService.borrowMovie(movieBorrow));
    }

    @GetMapping(path = "/borrow", produces = "application/json")
    public @ResponseBody ResponseEntity<RentedMovieShowPage> listRentedUnits() {
        return ResponseEntity.ok(borrowService.listRentsByLoggedUser(0));
    }

    @GetMapping(path = "/borrow/{page}", produces = "application/json")
    public @ResponseBody ResponseEntity<RentedMovieShowPage> listRentedUnits(@PathVariable(name = "page") int currentPage) {
        return ResponseEntity.ok(borrowService.listRentsByLoggedUser(currentPage));
    }

    @PostMapping(path = "/return", consumes = "application/json")
    public @ResponseBody ResponseEntity<BorrowReturnInformation> returnBorrowMovie(@RequestBody BorrowReturnInformation borrowReturnInformation) {
        return ResponseEntity.ok(borrowService.returnMovie(borrowReturnInformation));
    }
}
