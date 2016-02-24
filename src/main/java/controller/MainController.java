package controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import repository.GenreJDBCTemplate;
import repository.LinkJDBCTemplate;
import repository.UserJDBCTemplate;
import service.DbUser;
import service.Genre;
import service.Link;
import util.NextIDUtil;

@Controller
public class MainController {
	private static Logger log = Logger.getLogger(MainController.class);
	
	/**
	 * Handle request for open homepage<br/>
	 * Page only for online users
	 * @param model
	 * @return homepage
	 */
	@RequestMapping(value = "/index")
	public String openMainPage(Model model) {
		log.debug("Opening main page...");		
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/persistence.xml");
		UserJDBCTemplate userJdbc = (UserJDBCTemplate) context.getBean("userJDBCTemplate");
		DbUser user = userJdbc.getUser((String) ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
		model.addAttribute("user", user);
		
		GenreJDBCTemplate genreJdbc = (GenreJDBCTemplate) context.getBean("genreJDBCTemplate");
		List<Genre> genres = genreJdbc.getAll(user.getId());
		model.addAttribute("genres", genres);
		
		LinkJDBCTemplate linkJdbc = (LinkJDBCTemplate) context.getBean("linkJDBCTemplate");
		List<Link> links = linkJdbc.getAll(user.getId());
		model.addAttribute("links", links);
		
		setNextId(genreJdbc.getAll(), linkJdbc.getAll());
		
		return "index";
	}
	
	/**
	 * Set ID for next genre and link
	 * @param genres
	 * @param links
	 */
	private void setNextId(List<Genre> genres, List<Link> links) {
		int maxIdGenre = 1;
		for (Genre genre : genres) {
			if (genre.getId() > maxIdGenre) {
				maxIdGenre = genre.getId();
			}
		}
		NextIDUtil.setNextIDGenre(maxIdGenre + 1);
		
		int maxIdLink = 1;
		for (Link link : links) {
			if (link.getId() > maxIdLink) {
				maxIdLink = link.getId();
			}
		}
		NextIDUtil.setNextIDLink(maxIdLink + 1);
	}
	
}
