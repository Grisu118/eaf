package ch.fhnw.edu.rental.daos.jdbc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import ch.fhnw.edu.rental.daos.PriceCategoryDao;
import ch.fhnw.edu.rental.model.PriceCategory;
import ch.fhnw.edu.rental.model.PriceCategoryChildren;
import ch.fhnw.edu.rental.model.PriceCategoryNewRelease;
import ch.fhnw.edu.rental.model.PriceCategoryRegular;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class JdbcPriceCategoryDao extends JdbcDaoSupport implements PriceCategoryDao {

	RowMapper<PriceCategory> get = (rs, row) -> {
        PriceCategory r;
        switch (rs.getString("PRICECATEGORY_TYPE")) {
            case "Regular":
                r = new PriceCategoryRegular();
                break;
            case "Children" :
                r = new PriceCategoryChildren();
                break;
            case "New Release" :
                r = new PriceCategoryNewRelease();
                break;
            default:
                throw new IllegalArgumentException("Unknown PriceCategory");
        }
        r.setId(rs.getLong("PRICECATEGORY_ID"));
        return r;
    };

	@Override
	public PriceCategory getById(Long id) {
		return getJdbcTemplate().queryForObject("SELECT * FROM PRICECATEGORIES WHERE PRICECATEGORY_ID=?;", get, id);
	}

	@Override
	public List<PriceCategory> getAll() {
		return getJdbcTemplate().query("SELECT * FROM PRICECATEGORIES", get);
	}

	@Override
	public void saveOrUpdate(PriceCategory priceCategory) {
		if (priceCategory.getId() == null) {
            SimpleJdbcInsert i = new SimpleJdbcInsert(getJdbcTemplate());
            i.withTableName("PRICECATEGORIES")
                    .usingGeneratedKeyColumns("PRICECATEGORY_ID");
            Map<String, Object> parameters = new HashMap<>(1);
            parameters.put("PRICECATEGORY_TYPE", priceCategory.toString());
            priceCategory.setId((Long) i.executeAndReturnKey(parameters));
        } else {
            getJdbcTemplate().update("UPDATE PRICECATEGORIES SET PRICECATEGORY_TYPE=? WHERE PRICECATEGORY_ID = ?;", priceCategory.toString(), priceCategory.getId());
        }
	}

	@Override
	public void delete(PriceCategory priceCategory) {
		getJdbcTemplate().update("DELETE * FROM PRICECATEGORIES WHERE PRICECATEGORY_ID = ?;", priceCategory.getId());
        priceCategory.setId(null);
	}

}
