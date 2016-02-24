package repository;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import service.Link;

public class LinkJDBCTemplate {

	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);		
	}

	public void create(Link link) {
		String sql = "INSERT INTO link (id, address, description, id_user, id_genre) values (?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, link.getId(), link.getAddress(), link.getDescription(), link.getIdUser(), link.getIdGenre());
		
	}

	public void update(Link link) {
		String sql = "UPDATE link SET address=?, description=? WHERE id=? AND id_genre=? AND id_user=?";
		jdbcTemplate.update(sql, link.getAddress(), link.getDescription(), link.getId(), link.getIdGenre(), link.getIdUser());
	}

	public void delete(int idGenre, int idUser) {
		String sql = "DELETE FROM link WHERE id_genre=? AND id_user=?";
		jdbcTemplate.update(sql, idGenre, idUser);		
	}
	
	public void delete(int idLink, int idGenre, int idUser) {
		String sql = "DELETE FROM link WHERE id=? AND id_genre=? AND id_user=?";
		jdbcTemplate.update(sql, idLink, idGenre, idUser);		
	}

	public List<Link> getAll() {
		String sql = "SELECT * FROM link";
		List<Link> links = jdbcTemplate.query(sql, new LinkMapper());
		return links;
	}
	
	public List<Link> getAll(int idUser) {
		String sql = "SELECT * FROM link WHERE id_user=?";
		List<Link> links = jdbcTemplate.query(sql, new Object[]{idUser}, new LinkMapper());
		return links;
	}
	
	public Link getLink(int id) {
		String sql = "SELECT * FROM link WHERE id=?";
		Link link = jdbcTemplate.queryForObject(sql, new Object[]{id}, new LinkMapper());
		return link;	
	}

}
