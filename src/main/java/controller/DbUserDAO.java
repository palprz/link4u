package controller;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import repository.UserJDBCTemplate;
import service.DbUser;

public class DbUserDAO {

	private static Logger log = Logger.getLogger(DbUserDAO.class);
	
	/**
	 * Search database by username for get dbUser
	 * @param username
	 * @return dbUser
	 */
	public DbUser searchDatabase (String username) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/persistence.xml");
		UserJDBCTemplate userJdbc = (UserJDBCTemplate) context.getBean("userJDBCTemplate");
		for (DbUser dbUser : userJdbc.getAllUsers()) {
			if (dbUser.getUsername().equals(username)) {
				log.debug("User found!");
				return dbUser;
			}
		}
		
		return null;
	}
	
}
