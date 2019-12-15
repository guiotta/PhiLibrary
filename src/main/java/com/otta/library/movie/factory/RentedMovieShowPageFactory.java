package com.otta.library.movie.factory;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.otta.library.movie.entity.Unit;
import com.otta.library.movie.mapper.RentedMovieShowMapper;
import com.otta.library.movie.model.RentedMovieShow;
import com.otta.library.movie.model.pagination.RentedMovieShowPage;
import com.otta.library.pagination.Page;
import com.otta.library.pagination.factory.PaginationStepsFactory;
import com.otta.library.pagination.model.PageEndpoint;
import com.otta.library.pagination.model.PaginationSteps;

/**
 * Componente para construir um {@link Page}<{@link RentedMovieShow}>.
 * @author Guilherme
 *
 */
@Component
public class RentedMovieShowPageFactory {
    private final PaginationStepsFactory paginationStepsFactory;
    private final RentedMovieShowMapper rentedMovieShowMapper;

    @Autowired
    public RentedMovieShowPageFactory(PaginationStepsFactory paginationStepsFactory,
            RentedMovieShowMapper rentedMovieShowMapper) {
        this.paginationStepsFactory = paginationStepsFactory;
        this.rentedMovieShowMapper = rentedMovieShowMapper;
    }

    public Page<RentedMovieShow> create(org.springframework.data.domain.Page<Unit> unitPage, PageEndpoint pageEndpoint,
            int currentPage) {
        PaginationSteps paginationSteps = paginationStepsFactory.create(pageEndpoint, currentPage, unitPage.hasPrevious(),
                unitPage.hasNext());
        List<RentedMovieShow> rentedUnitsToShow = unitPage.stream().map(unit -> rentedMovieShowMapper.map(unit))
                .collect(Collectors.toList());

        return new RentedMovieShowPage(currentPage, unitPage.getTotalPages(), unitPage.getNumberOfElements(),
                rentedUnitsToShow, paginationSteps.getNext(), paginationSteps.getPrevious());
    }
}
