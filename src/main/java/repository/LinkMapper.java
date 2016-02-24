package repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import service.Link;

public class LinkMapper implements RowMapper<Link>{

	@Override
	public Link mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new Link(rs.getInt("id"), rs.getString("address"), rs.getString("description"), rs.getInt("id_user"), rs.getInt("id_genre"));
	}

}
