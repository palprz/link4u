package controller;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import repository.LinkJDBCTemplate;
import service.Link;
import util.NextIDUtil;

@Controller
@RequestMapping(value = "/link")
public class LinkController {
	
	private static Logger log = Logger.getLogger(LinkController.class);
	private ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/persistence.xml");

	/**
	 * Handle request for add link
	 * @param address
	 * @param description
	 * @param idGenre
	 * @param idUser
	 * @return ID of created link
	 */
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public @ResponseBody String addLink(
			@RequestParam("address") String address,
			@RequestParam("description") String description,
			@RequestParam("idgenre") int idGenre,
			@RequestParam("iduser") int idUser) {
		log.debug("Adding new link...");
		Link link = new Link(NextIDUtil.getNextIDLink(), address, description, idUser, idGenre);

		LinkJDBCTemplate linkJdbc = (LinkJDBCTemplate) context.getBean("linkJDBCTemplate");
		linkJdbc.create(link);
		
		NextIDUtil.setNextIDLink(link.getId() + 1);
		
		StringBuilder sb = new StringBuilder();
		sb.append(link.getId());
		return sb.toString();
	}
	
	/**
	 * Handle request for edit link
	 * @param id
	 * @param address
	 * @param description
	 * @param idGenre
	 * @param idUser
	 * @return msg
	 */
	@RequestMapping(value="/edit", method=RequestMethod.GET)
	public @ResponseBody String editLink(
			@RequestParam("id") int id,
			@RequestParam("address") String address,
			@RequestParam("description") String description,
			@RequestParam("idgenre") int idGenre,
			@RequestParam("iduser") int idUser) {
		log.debug("Editing link...");
		Link link = new Link(id, address, description, idGenre, idUser);
		
		LinkJDBCTemplate linkJdbc = (LinkJDBCTemplate) context.getBean("linkJDBCTemplate");
		linkJdbc.update(link);
		return "Link was edited!";
	}
	
	/**
	 * Handle request for delete link
	 * @param idLink
	 * @param idGenre
	 * @param idUser
	 * @return msg
	 */
	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public @ResponseBody String deleteLink(
			@RequestParam("id") int idLink,
			@RequestParam("idgenre") int idGenre,
			@RequestParam("iduser") int idUser) {
		log.debug("Deleting link...");
		LinkJDBCTemplate linkJdbc = (LinkJDBCTemplate) context.getBean("linkJDBCTemplate");	
		linkJdbc.delete(idLink, idGenre, idUser);
		return "Link was removed!";
	}
	
}
