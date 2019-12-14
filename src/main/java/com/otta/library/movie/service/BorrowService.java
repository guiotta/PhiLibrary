package com.otta.library.movie.service;

import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.otta.library.movie.entity.Borrow;
import com.otta.library.movie.entity.Movie;
import com.otta.library.movie.entity.Unit;
import com.otta.library.movie.model.BorrowReturnInformation;
import com.otta.library.movie.model.MovieBorrow;
import com.otta.library.movie.model.RentedMovieShow;
import com.otta.library.movie.model.RentedMovieShowPage;
import com.otta.library.movie.repository.MovieRepository;
import com.otta.library.movie.repository.UnitRepository;
import com.otta.library.user.entity.User;
import com.otta.library.user.repository.UserRepository;

@Service
public class BorrowService {
    private static final int DEFAULT_ELEMENTS_QUANTITY = 10;
    private static final int CHANGE_PAGE_OFFSET = 1;

    private final UserRepository userRepository;
    private final UnitRepository unitRepository;
    private final MovieRepository movieRepository;

    @Autowired
    public BorrowService(UserRepository userRepository, UnitRepository unitRepository,
            MovieRepository movieRepository) {
        this.userRepository = userRepository;
        this.unitRepository = unitRepository;
        this.movieRepository = movieRepository;
    }

    @Transactional
    public MovieBorrow borrowMovie(MovieBorrow movieBorrow) {
        Movie movie = movieRepository.findById(movieBorrow.getIdMovie()).orElseThrow(IllegalArgumentException::new);
        // TODO: Componentizar as três linhas abaixo.
        Collection<Unit> unitsByMovie = unitRepository.findByMovie(movie);
        Collection<Unit> rentedUnitsByMovie = unitRepository.findByMovieAndRented(movie);
        Unit unit = unitsByMovie.stream().filter(unitToFilter -> !rentedUnitsByMovie.contains(unitToFilter)).findFirst().orElseThrow(IllegalArgumentException::new);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(auth.getName());

        Borrow borrow = new Borrow();
        borrow.setBegin(Calendar.getInstance());
        borrow.setUser(user);
        borrow.setIdUnit(unit.getId());
        unit.addBorrow(borrow);

        unitRepository.save(unit);
        return movieBorrow;
    }

    @Transactional
    public BorrowReturnInformation returnMovie(BorrowReturnInformation borrowReturnInformation) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(auth.getName());

        if (user.getId() != borrowReturnInformation.getUserId()) {
            throw new IllegalArgumentException("Could not return a Borrow for another User.");
        }

        Unit unitWithBorrowToReturn = unitRepository.findByBorrowId(borrowReturnInformation.getBorrowId()).orElseThrow(IllegalArgumentException::new);
        unitWithBorrowToReturn.getBorrowings().stream().filter(borrow -> borrow.getId() == borrowReturnInformation.getBorrowId()).findFirst().get().setEnd(Calendar.getInstance());
        unitRepository.save(unitWithBorrowToReturn);

        return borrowReturnInformation;
    }

    public RentedMovieShowPage listRentsByLoggedUser(int currentPage) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(auth.getName());
        Page<Unit> unitsPage = unitRepository.findRentedUnitsByUser(user, PageRequest.of(currentPage, DEFAULT_ELEMENTS_QUANTITY));

        String next = unitsPage.hasNext() ? "http://localhost:8080/api/movie/borrow/" + (currentPage + CHANGE_PAGE_OFFSET) : null;
        String previous = unitsPage.hasPrevious() ? "http://localhost:8080/api/movie/borrow/" + (currentPage - CHANGE_PAGE_OFFSET) : null;
        int totalPages = unitsPage.getTotalPages();
        int elementsInPage = unitsPage.getNumberOfElements();
        List<RentedMovieShow> rentedUnitsToShow = unitsPage.stream()
                .map(unit -> new RentedMovieShow(unit.getMovie().getId(), unit.getMovie().getName(), unit.getId()))
                .collect(Collectors.toList());

        return new RentedMovieShowPage(currentPage, totalPages, elementsInPage, rentedUnitsToShow, next, previous);
    }
}
