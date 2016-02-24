package repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import service.DbUser;

public class UserMapper implements RowMapper<DbUser>{

	@Override
	public DbUser mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new DbUser(rs.getInt("id"), rs.getString("username"), rs.getString("password"));
	}

}
