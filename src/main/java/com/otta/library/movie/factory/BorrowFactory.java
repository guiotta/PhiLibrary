package com.otta.library.movie.factory;

import java.util.Calendar;

import org.springframework.stereotype.Component;

import com.otta.library.movie.entity.Borrow;
import com.otta.library.user.entity.User;

/**
 * Componente para construir um {@link Borrow}.
 * @author Guilherme
 *
 */
@Component
public class BorrowFactory {

    public Borrow create(User user, long unitId) {
        Borrow borrow = new Borrow();

        borrow.setBegin(Calendar.getInstance());
        borrow.setUser(user);
        borrow.setIdUnit(unitId);

        return borrow;
    }
}
