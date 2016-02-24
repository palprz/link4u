package controller;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import repository.GenreJDBCTemplate;
import repository.LinkJDBCTemplate;
import service.Genre;
import util.NextIDUtil;

@Controller
@RequestMapping(value = "/genre")
public class GenreController {
	
	private static Logger log = Logger.getLogger(GenreController.class);
	private ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/persistence.xml");
	
	/**
	 * Handle request for add genre
	 * @param name
	 * @param idUser
	 * @return ID of created genre
	 */
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public @ResponseBody String addGenre(
			@RequestParam("name") String name,
			@RequestParam("iduser") int idUser) {
		log.debug("Adding new genre...");
		Genre genre = new Genre(NextIDUtil.getNextIDGenre(), name, idUser);
		
		GenreJDBCTemplate genreJdbc = (GenreJDBCTemplate) context.getBean("genreJDBCTemplate");
		genreJdbc.create(genre);
		
		NextIDUtil.setNextIDGenre(genre.getId() + 1);
		StringBuilder sb = new StringBuilder();
		sb.append(genre.getId());
		return sb.toString();
	}
	
	/**
	 * Handle request for edit genre
	 * @param id
	 * @param name
	 * @param idUser
	 * @return msg
	 */
	@RequestMapping(value="/edit", method=RequestMethod.GET)
	public @ResponseBody String editGenre(
			@RequestParam("id") int id,
			@RequestParam("name") String name,
			@RequestParam("iduser") int idUser) {
		log.debug("Editing user...");
		Genre genre = new Genre(id, name, idUser);
		
		GenreJDBCTemplate genreJdbc = (GenreJDBCTemplate) context.getBean("genreJDBCTemplate");
		genreJdbc.update(genre);
		return "Genre was added!";
	}
	
	/**
	 * Handle request for delete genre
	 * @param idGenre
	 * @param idUser
	 * @return msg
	 */
	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public @ResponseBody String deleteGenre (
			@RequestParam("idgenre") int idGenre,
			@RequestParam("iduser") int idUser) {
		log.debug("Deleting user...");
		LinkJDBCTemplate linkJdbc = (LinkJDBCTemplate) context.getBean("linkJDBCTemplate");
		linkJdbc.delete(idGenre, idUser);
		
		GenreJDBCTemplate genreJdbc = (GenreJDBCTemplate) context.getBean("genreJDBCTemplate");
		genreJdbc.delete(idGenre, idUser);
		return "Genre was deleted!";
	}
}
