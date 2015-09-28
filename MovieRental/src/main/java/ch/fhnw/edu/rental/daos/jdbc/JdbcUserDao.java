package ch.fhnw.edu.rental.daos.jdbc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ch.fhnw.edu.rental.daos.RentalDao;
import ch.fhnw.edu.rental.daos.UserDao;
import ch.fhnw.edu.rental.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class JdbcUserDao extends JdbcDaoSupport implements UserDao {

	private RentalDao rentalDao;

	public void setRentalDao(RentalDao rentalDao) {
		this.rentalDao = rentalDao;
	}

	@Override
	public User getById(Long id) {
		JdbcTemplate template = getJdbcTemplate();
        ResultSetExtractor<User> res = (rs) -> {
            User u =  new User(rs.getString("USER_NAME"), rs.getString("USER_FIRSTNAME"));
            u.setId(rs.getLong("USER_ID"));
            u.setEmail(rs.getString("USER_EMAIL"));
            return u;
        };
		return template.query("SELECT * FROM USERS WHERE USER_ID = ?;", res, id);
	}

	@Override
	public List<User> getAll() {
		return getJdbcTemplate().query("SELECT * FROM USERS;", (rs, row) -> {
            User u = new User(rs.getString("USER_NAME"), rs.getString("USER_FIRSTNAME"));
            u.setId(rs.getLong("USER_ID"));
            u.setEmail(rs.getString("USER_EMAIL"));
            return u;
        });
	}

	@Override
	public List<User> getByName(String name) {
        JdbcTemplate template = getJdbcTemplate();
        return template.query("SELECT * FROM USERS WHERE USER_NAME = ?;", (rs, row) -> {
            User u =  new User(rs.getString("USER_NAME"), rs.getString("USER_FIRSTNAME"));
            u.setId(rs.getLong("USER_ID"));
            u.setEmail(rs.getString("USER_EMAIL"));
            return u;
        }, name);
	}

	@Override
	public void saveOrUpdate(User user) {
		if (user.getId() == null) {
            SimpleJdbcInsert i = new SimpleJdbcInsert(getJdbcTemplate());
            i.withTableName("USERS")
                    .usingGeneratedKeyColumns("USER_ID");
            Map<String, Object> parameters = new HashMap<>(3);
            parameters.put("USER_FIRSTNAME", user.getFirstName());
            parameters.put("USER_NAME", user.getLastName());
            parameters.put("USER_EMAIL", user.getEmail());
            user.setId((Long) i.executeAndReturnKey(parameters));
        } else {
            getJdbcTemplate().update("UPDATE USERS SET USER_NAME=?, USER_FIRSTNAME=?, USER_EMAIL, WHERE USER_ID=?;", user.getLastName(), user.getFirstName(), user.getEmail(), user.getId());
        }
	}

	@Override
	public void delete(User user) {
		if (user.getId() != null) {
            getJdbcTemplate().update("DELETE * FROM USERS WHERE USER_ID=?;", user.getId());
        }
	}
}
