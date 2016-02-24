package repository;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import service.DbUser;

public class UserJDBCTemplate {

	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public void create(DbUser user) {
		String sql = "INSERT INTO user (id, username, password) values (?, ?, ?)";
		jdbcTemplate.update(sql, user.getId(), user.getUsername(), user.getPassword());
	}
	
	public void update(DbUser user) {
		String sql = "UPDATE user SET password=? WHERE id=?";
		jdbcTemplate.update(sql, user.getPassword(), user.getId());
	}
	
	public List<DbUser> getAllUsers() {
		String sql = "SELECT * FROM user";
		List<DbUser> users = jdbcTemplate.query(sql, new UserMapper());
		return users;
	}

	public List<String> getAllUsernames() {
		String sql = "SELECT username FROM user";
		List<String> usernames = (List<String>) jdbcTemplate.queryForList(sql, String.class);
		return usernames;
	}
	
	public DbUser getUser(int id) {
		String sql = "SELECT * FROM user WHERE id=?";
		DbUser user = jdbcTemplate.queryForObject(sql, new Object[]{id}, new UserMapper());
		return user;
	}

	public DbUser getUser(String username) {
		String sql = "SELECT * FROM user WHERE username=?";
		try {
			DbUser user = jdbcTemplate.queryForObject(sql, new Object[]{username}, new UserMapper());
			return user;
		} catch(EmptyResultDataAccessException e) {
			return null;
		}
	}
	
}
