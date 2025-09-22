package ra.session6.service;

import ra.session6.model.entity.Movie;

import java.util.List;


public interface MovieService {
    List<Movie> findAll();
    boolean create(Movie movie);
    boolean update(Movie movie);
    boolean delete(int id);
    Movie findById(int id);
}
