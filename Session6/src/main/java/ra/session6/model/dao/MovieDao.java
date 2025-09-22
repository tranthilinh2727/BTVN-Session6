package ra.session6.model.dao;

import ra.session6.model.entity.Movie;

import java.util.List;

public interface MovieDao {
    List<Movie> findAll();
    boolean create(Movie movies);
    boolean update(Movie movies);
    boolean delete(int movies);
    Movie findById(Long id);
}
