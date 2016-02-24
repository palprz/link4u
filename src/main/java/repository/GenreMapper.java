package repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import service.Genre;

public class GenreMapper implements RowMapper<Genre>{

	@Override
	public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new Genre(rs.getInt("id"), rs.getString("name"), rs.getInt("id_user"));
	}

}
