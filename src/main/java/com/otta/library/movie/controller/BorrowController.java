package com.otta.library.movie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.otta.library.movie.model.BorrowReturnInformation;
import com.otta.library.movie.model.MovieBorrow;
import com.otta.library.movie.model.RentedMovieShow;
import com.otta.library.movie.service.BorrowService;
import com.otta.library.pagination.Page;
import com.otta.library.pagination.model.PageEndpoint;

/**
 * {@link RestController} para as operações HTTP dos Aluguéis.
 * 
 * @author Guilherme
 *
 */
@RestController
@RequestMapping("/api/borrow")
public class BorrowController {
    private final BorrowService borrowService;

    @Autowired
    public BorrowController(BorrowService borrowService) {
        this.borrowService = borrowService;
    }

    @GetMapping(produces = "application/json")
    public @ResponseBody ResponseEntity<Page<RentedMovieShow>> listRentedUnits() {
        return ResponseEntity.ok(borrowService.listRentsByLoggedUser(PageEndpoint.BORROW, 0));
    }

    @GetMapping(path = "/{page}", produces = "application/json")
    public @ResponseBody ResponseEntity<Page<RentedMovieShow>> listRentedUnits(
            @PathVariable(name = "page") int currentPage) {
        return ResponseEntity.ok(borrowService.listRentsByLoggedUser(PageEndpoint.BORROW, currentPage));
    }

    @PostMapping(consumes = "application/json")
    public @ResponseBody ResponseEntity<MovieBorrow> borrowMovie(@RequestBody MovieBorrow movieBorrow) {
        return ResponseEntity.ok(borrowService.borrowMovie(movieBorrow));
    }

    @PutMapping(consumes = "application/json")
    public @ResponseBody ResponseEntity<BorrowReturnInformation> returnBorrowMovie(
            @RequestBody BorrowReturnInformation borrowReturnInformation) {
        return ResponseEntity.ok(borrowService.returnMovie(borrowReturnInformation));
    }
}
