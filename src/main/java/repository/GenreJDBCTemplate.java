package repository;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import service.Genre;

public class GenreJDBCTemplate {

	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);		
	}

	public void create(Genre genre) {
		String sql = "INSERT INTO genre (id, name, id_user) values (?, ?, ?)";
		jdbcTemplate.update(sql, genre.getId(), genre.getName(), genre.getIDUser());
		
	}

	public void update(Genre genre) {
		String sql = "UPDATE genre SET name=? WHERE id=? AND id_user=?";
		jdbcTemplate.update(sql, genre.getName(), genre.getId(), genre.getIDUser());
	}

	public void delete(int idGenre, int idUser) {
		String sql = "DELETE FROM genre WHERE id=? AND id_user=?";
		jdbcTemplate.update(sql, idGenre, idUser);	
		
	}

	public List<Genre> getAll() {
		String sql = "SELECT * FROM genre";
		List<Genre> genres = jdbcTemplate.query(sql, new GenreMapper());
		return genres;
	}
	
	public List<Genre> getAll(int idUser) {
		String sql = "SELECT * FROM genre WHERE id_user=?";
		List<Genre> genres = jdbcTemplate.query(sql, new Object[]{idUser}, new GenreMapper());
		return genres;
	}
	
	public Genre getGenre(int id) {
		String sql = "SELECT * FROM genre WHERE id=?";
		Genre genre = jdbcTemplate.queryForObject(sql, new Object[]{id}, new GenreMapper());
		return genre;	
	}

}
