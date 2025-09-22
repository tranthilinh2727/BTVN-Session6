package ra.session6.service;

import ra.session6.model.dao.MovieDao;
import ra.session6.model.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MovieServiceImp implements MovieService{
    @Autowired
    private MovieDao movieDao;

    @Override
    public List<Movie> findAll() {
        return movieDao.findAll();
    }

    @Override
    public boolean create(Movie movie) {
        return movieDao.create(movie);
    }

    @Override
    public boolean update(Movie movie) {
        return movieDao.update(movie);
    }

    @Override
    public boolean delete(int id) {
        return movieDao.delete(id);
    }

    @Override
    public Movie findById(int id) {
        return movieDao.findById((long) id);
    }
}