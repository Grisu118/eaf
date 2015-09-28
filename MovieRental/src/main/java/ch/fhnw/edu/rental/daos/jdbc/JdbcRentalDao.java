package ch.fhnw.edu.rental.daos.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


import ch.fhnw.edu.rental.daos.MovieDao;
import ch.fhnw.edu.rental.daos.RentalDao;
import ch.fhnw.edu.rental.daos.UserDao;
import ch.fhnw.edu.rental.model.Movie;
import ch.fhnw.edu.rental.model.Rental;
import ch.fhnw.edu.rental.model.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class JdbcRentalDao extends JdbcDaoSupport implements RentalDao {

	private MovieDao movieDao;
	private UserDao userDao;

    private RowMapper<Rental> get = (rs, row) -> {
        Rental r = new Rental(userDao.getById(rs.getLong("USER_ID")),
                movieDao.getById(rs.getLong("MOVIE_ID")),
                rs.getInt("RENTAL_RENTALDAYS"),
                rs.getDate("RENTAL_RENTALDATE"));
        r.setId(rs.getLong("MOVIE_ID"));
        return r;
    };

	public void setMovieDao(MovieDao movieDao) {
		this.movieDao = movieDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public Rental getById(Long id) {
		return getJdbcTemplate().queryForObject("SELECT * FROM RENTALS WHERE RENTAL_ID = ?;", get, id);
	}

	@Override
	public List<Rental> getAll() {
		return getJdbcTemplate().query("SELECT * FROM RENTALS;", get);
	}

	@Override
	public List<Rental> getRentalsByUser(User user) {
		return  getJdbcTemplate().query("SELECT * FROM RENTALS WHERE USER_ID = ?;", get, user.getId());
	}

	@Override
	public void saveOrUpdate(Rental rental) {
		if (rental.getId() == null) {
            SimpleJdbcInsert i = new SimpleJdbcInsert(getJdbcTemplate());
            i.withTableName("RENTALS")
                    .usingGeneratedKeyColumns("RENTAL_ID");
            Map<String, Object> parameters = new HashMap<>(4);
            parameters.put("RENTAL_RENTALDATE", rental.getRentalDate());
            parameters.put("RENTAL_RENTALDAYS", rental.getRentalDays());
            parameters.put("USER_ID", rental.getUser().getId());
            parameters.put("MOVIE_ID", rental.getMovie().getId());
            rental.setId((Long) i.executeAndReturnKey(parameters));
        } else {
            getJdbcTemplate().update("UPDATE RENTALS SET RENTAL_RENTALDATE=?, RENTAL_RENTALDAYS=?, USER_ID=?, MOVIE_ID=? WHERE RENTAL_ID=?;", rental.getRentalDate(),
                    rental.getRentalDays(), rental.getUser().getId(), rental.getMovie().getId(), rental.getId());
        }

	}

	@Override
	public void delete(Rental rental) {
		getJdbcTemplate().update("DELETE * FROM RENTALS WHERE RENTAL_ID=?;", rental.getId());
	}

	private Rental createRental(ResultSet rs) throws SQLException {
		User user = userDao.getById(rs.getLong("USER_ID"));
		return createRental(rs, user);
	}

	private Rental createRental(ResultSet rs, User u) throws SQLException {
		Movie m = movieDao.getById(rs.getLong("MOVIE_ID"));
		if (!m.isRented())
			throw new RuntimeException("movie must be rented if read from DB");
		m.setRented(false);
		Rental r = new Rental(u, m, rs.getInt("RENTAL_RENTALDAYS"));
		m.setRented(true);
		r.setId(rs.getLong("RENTAL_ID"));
		return r;
	}

}
