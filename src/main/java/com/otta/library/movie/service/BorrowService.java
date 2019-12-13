package com.otta.library.movie.service;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.otta.library.movie.entity.Borrow;
import com.otta.library.movie.entity.Unit;
import com.otta.library.movie.model.MovieBorrow;
import com.otta.library.movie.repository.UnitRepository;
import com.otta.library.user.entity.User;
import com.otta.library.user.repository.UserRepository;

@Service
public class BorrowService {
    private final UserRepository userRepository;
    private final UnitRepository unitRepository;

    @Autowired
    public BorrowService(UserRepository userRepository, UnitRepository unitRepository) {
        this.userRepository = userRepository;
        this.unitRepository = unitRepository;
    }

    public MovieBorrow borrowMovie(MovieBorrow movieBorrow) {
        Unit unit = unitRepository.findById(Long.valueOf(movieBorrow.getIdUnit())).orElseThrow(IllegalArgumentException::new);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(auth.getName());

        Borrow borrow = new Borrow();
        borrow.setBegin(Calendar.getInstance());
        borrow.setUser(user);
        borrow.setUnit(unit);
        unit.addBorrow(borrow);

        unitRepository.save(unit);
        return movieBorrow;
    }
}
