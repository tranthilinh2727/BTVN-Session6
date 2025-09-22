package ra.session6.model.dao;

import org.springframework.stereotype.Repository;
import ra.session6.until.ConnectionDB;
import ra.session6.model.entity.Movie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Repository
public class MovieDaoImpl implements MovieDao {

    @Override
    public List<Movie> findAll() {
        List<Movie> list = new ArrayList<>();
        String sql = "{CALL GetAllMovies()}";

        Connection conn = null;
        try {
            conn = ConnectionDB.openConnection();
            CallableStatement cs = conn.prepareCall(sql);
            ResultSet rs = cs.executeQuery();

            while (rs.next()) {
                Movie m = new Movie();
                m.setId((int) rs.getLong("id"));
                m.setTitle(rs.getString("title"));
                m.setDirector(rs.getString("director"));
                m.setGenre(rs.getString("genre"));
                m.setDescription(rs.getString("description"));
                m.setDuration(rs.getInt("duration"));
                m.setLanguage(rs.getString("language"));
                list.add(m);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return list;
    }

    @Override
    public boolean create(Movie movie) {
        String sql = "{CALL AddMovie(?,?,?,?,?,?)}";
        Connection conn = null;
        try {
            conn = ConnectionDB.openConnection();
            CallableStatement cs = conn.prepareCall(sql);

            cs.setString(1, movie.getTitle());
            cs.setString(2, movie.getDirector());
            cs.setString(3, movie.getGenre());
            cs.setString(4, movie.getDescription());
            cs.setInt(5, movie.getDuration());
            cs.setString(6, movie.getLanguage());

            return cs.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return false;
    }

    @Override
    public boolean update(Movie movie) {
        String sql = "{CALL UpdateMovie(?,?,?,?,?,?,?)}";
        Connection conn = null;
        try {
            conn = ConnectionDB.openConnection();
            CallableStatement cs = conn.prepareCall(sql);

            cs.setLong(1, movie.getId());
            cs.setString(2, movie.getTitle());
            cs.setString(3, movie.getDirector());
            cs.setString(4, movie.getGenre());
            cs.setString(5, movie.getDescription());
            cs.setInt(6, movie.getDuration());
            cs.setString(7, movie.getLanguage());

            return cs.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        String sql = "{CALL DeleteMovie(?)}";
        Connection conn = null;
        try {
            conn = ConnectionDB.openConnection();
            CallableStatement cs = conn.prepareCall(sql);
            cs.setInt(1,id);

            return cs.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return false;
    }

    @Override
    public Movie findById(Long id) {
        String sql = "SELECT * FROM Movies WHERE id = ?";
        Connection conn = null;
        try {
            conn = ConnectionDB.openConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Movie m = new Movie();
                m.setId((int) rs.getLong("id"));
                m.setTitle(rs.getString("title"));
                m.setDirector(rs.getString("director"));
                m.setGenre(rs.getString("genre"));
                m.setDescription(rs.getString("description"));
                m.setDuration(rs.getInt("duration"));
                m.setLanguage(rs.getString("language"));
                return m;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return new Movie();}}
