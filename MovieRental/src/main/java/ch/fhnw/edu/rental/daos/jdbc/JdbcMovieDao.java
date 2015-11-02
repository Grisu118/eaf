package ch.fhnw.edu.rental.daos.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import ch.fhnw.edu.rental.daos.MovieDao;
import ch.fhnw.edu.rental.daos.PriceCategoryDao;
import ch.fhnw.edu.rental.model.Movie;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class JdbcMovieDao extends JdbcDaoSupport implements MovieDao {

	private PriceCategoryDao priceCategoryDao;

	public void setPriceCategoryDao(PriceCategoryDao priceCategoryDao) {
		this.priceCategoryDao = priceCategoryDao;
	}

	@Override
	public Movie getById(Long id) {
		return getJdbcTemplate().queryForObject("SELECT * FROM MOVIES WHERE MOVIE_ID = ?",
                (rs, row) -> createMovie(rs),
                id);
	}

	@Override
	public List<Movie> getByTitle(String name) {
		return getJdbcTemplate().query("SELECT * FROM MOVIES WHERE MOVIE_TITLE = ?", (rs, row) -> createMovie(rs), name);
	}

	@Override
	public List<Movie> getAll() {
		List<Movie> movies = new LinkedList<Movie>();
		Connection conn = null;
		try {
			conn = ds.getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("select * from MOVIES");
			while (rs.next()) {
				movies.add(createMovie(rs));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
			}
		}
		return movies;
	}

	@Override
	public void saveOrUpdate(Movie movie) {
		Connection conn = null;
		try {
			conn = ds.getConnection();

			PreparedStatement pst;
			if (movie.getId() == null) { // insert
				long id = 0;
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery("select max(MOVIE_ID) from MOVIES");
				if (rs.next()) {
					id = rs.getLong(1) + 1;
				}
				movie.setId(id);

				pst = conn.prepareStatement(
						"INSERT INTO MOVIES (MOVIE_ID, MOVIE_TITLE, MOVIE_RELEASEDATE, MOVIE_RENTED, PRICECATEGORY_FK) VALUES (?,?,?,?,?)");
				pst.setLong(1, id);
				pst.setString(2, movie.getTitle());
				pst.setDate(3, new Date(movie.getReleaseDate().getTime()));
				pst.setBoolean(4, movie.isRented());
				pst.setLong(5, movie.getPriceCategory().getId());
				pst.execute();
			} else { // update
				pst = conn.prepareStatement(
						"UPDATE MOVIES SET MOVIE_TITLE=?, MOVIE_RELEASEDATE=?, MOVIE_RENTED=?, PRICECATEGORY_FK=? where MOVIE_ID=?");
				pst.setLong(5, movie.getId());
				pst.setString(1, movie.getTitle());
				pst.setDate(2, new Date(movie.getReleaseDate().getTime()));
				pst.setBoolean(3, movie.isRented());
				pst.setLong(4, movie.getPriceCategory().getId());
				pst.execute();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}

	@Override
	public void delete(Movie movie) {
		Connection conn = null;
		try {
			conn = ds.getConnection();
			Statement st = conn.createStatement();
			st.execute("delete from MOVIES where MOVIE_ID = " + movie.getId());
			movie.setId(null);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}

	private Movie createMovie(ResultSet rs) throws SQLException {
		long priceCategory = rs.getLong("PRICECATEGORY_FK");
		Movie m = new Movie(rs.getString("MOVIE_TITLE"), rs.getDate("MOVIE_RELEASEDATE"),
				priceCategoryDao.getById(priceCategory));
		m.setId(rs.getLong("MOVIE_ID"));
		m.setRented(rs.getBoolean("MOVIE_RENTED"));
		return m;
	}

}
