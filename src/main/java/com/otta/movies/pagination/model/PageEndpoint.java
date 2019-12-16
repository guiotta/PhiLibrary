package com.otta.movies.pagination.model;

/**
 * Enum listando informações dos Endpoints da aplicação que utilizam paginação.
 * 
 * @author Guilherme
 *
 */
public enum PageEndpoint {
    MOVIE(1, "/api/movie/"),
    SEARCH(2, "/api/movie/search/"),
    BORROW(3, "/api/borrow/");

    private int id;
    private String specificUrl;

    private PageEndpoint(int id, String specificUrl) {
        this.id = id;
        this.specificUrl = specificUrl;
    }

    public int getId() {
        return id;
    }

    public String getSpecificUrl() {
        return specificUrl;
    }
}
