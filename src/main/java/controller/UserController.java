package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import repository.UserJDBCTemplate;
import service.DbUser;
import util.NextIDUtil;

@Controller
public class UserController {
	
	private static Logger log = Logger.getLogger(UserController.class);
	private ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/persistence.xml");
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	/**
	 * Handle request for open sign up page
	 * @param model
	 * @return signup page
	 */
	@RequestMapping(value="/signup", method=RequestMethod.GET)
	public String signUpUser(Model model) {
		model.addAttribute("command", new DbUser());		
		return "signup";
	}

	/**
	 * Handle request for creating new user
	 * @param secondPassword - repeated password
	 * @param user - username and password of new user
	 * @param request
	 * @param model
	 * @return homepage
	 */
	@RequestMapping(value="/signup", method=RequestMethod.POST)
	public String formSignUpUser(@RequestParam("secondPassword") String secondPassword, 
			@ModelAttribute DbUser user,
			HttpServletRequest request,
			Model model) {
		log.debug("Adding new user...");
		
		UserJDBCTemplate userJdbc = (UserJDBCTemplate) context.getBean("userJDBCTemplate");
		List<String> usernames = userJdbc.getAllUsernames();
		if (usernames.contains(user.getUsername())) {
			model.addAttribute("error", "There is already username like: " + user.getUsername() + ".");
			return "signup";
		}
		
		if (!user.getPassword().equals(secondPassword)) {
			model.addAttribute("error", "You have entered an invalid repeat of new password!");
			return "signup";	
		}

		//1. create userdetails for auto-login
		log.debug("Creating userdetails...");
		user.setId(setNextIdUser());
		SecurityController secCon = new SecurityController();
		UserDetails userDetails = secCon.initUserWithRawPassword(user);
		
		//2. hash password
		log.debug("Hashing password...");
		PasswordEncoder encoder = new Md5PasswordEncoder(); //TODO this is old way with get crypt -> change Spring Security to 4.0.2 and use BCrypt
		String hashedPassword = encoder.encodePassword(user.getPassword(), null);
		user.setPassword(hashedPassword);
		
		//3. create user
		log.debug("Creating user...");
		userJdbc.create(user);
		
		//4. create session for new user
		log.debug("Creating session for user...");
		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken (userDetails, secondPassword, userDetails.getAuthorities());
		authenticationManager.authenticate(auth);
		if(auth.isAuthenticated()) {
		    SecurityContextHolder.getContext().setAuthentication(auth);
		}
		log.debug("Done!");
		
		NextIDUtil.setNextIDUser(user.getId() + 1);	
		model.addAttribute("error", "");
		return "redirect:/";
	}
	
	/**
	 * Handle request for open login page
	 * @param isError - variable for showing message with error if there is something wrong
	 * @param model
	 * @return login page
	 */
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String loginUser(@RequestParam(value="error", required=false) boolean isError, Model model) {
		model.addAttribute("command", new DbUser());
		if (isError) {
			model.addAttribute("error", "You have entered an invalid username or password!");
		} else {
			model.addAttribute("error", "");
		}
		
		return "login";
	}
	
	/**
	 * Handle request for open edit account page
	 * @param model
	 * @return account page
	 */
	@RequestMapping(value="/account", method=RequestMethod.GET)
	public String accountUser(Model model) {
		model.addAttribute("command", new DbUser());
		return "account";
	}
	
	/**
	 * Handle request for edit account page
	 * @param currentPassword
	 * @param newPassword
	 * @param secondNewPassword
	 * @param model
	 * @return homepage
	 */
	@RequestMapping(value="/account", method=RequestMethod.POST)
	public String formAccountUser(@RequestParam("currentPassword") String currentPassword, 
			@RequestParam("newPassword") String newPassword, 
			@RequestParam("secondNewPassword") String secondNewPassword,
			Model model) {
		UserJDBCTemplate userJdbc = (UserJDBCTemplate) context.getBean("userJDBCTemplate");
		DbUser user = userJdbc.getUser((String) ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
		if (!newPassword.equals(secondNewPassword)) {
			model.addAttribute("error", "You have entered an invalid repeat of new password!");
			return "account";
		}

		PasswordEncoder encoder = new Md5PasswordEncoder();
		String hashedCurrentPassword = encoder.encodePassword(currentPassword, null);
		if (!user.getPassword().equals(hashedCurrentPassword)) {
			model.addAttribute("error", "You have entered an invalid current password!");
			return "account";
		}

		String hashedNewPassword = encoder.encodePassword(newPassword, null);
		user.setPassword(hashedNewPassword);				
		
		userJdbc.update(user);

		model.addAttribute("error", "");
		return "redirect:/";
	}
	
	/**
	 * Get from database nextIDUser, set to the static int and return it for set new user.
	 * @return nextIdUser
	 */
	private int setNextIdUser() {
		UserJDBCTemplate userJdbc = (UserJDBCTemplate) context.getBean("userJDBCTemplate");
		int maxIdUser = 1;
		for (DbUser dbUser : userJdbc.getAllUsers()) {
			if (dbUser.getId() > maxIdUser) {
				maxIdUser = dbUser.getId();
			}
		}
		NextIDUtil.setNextIDUser(maxIdUser + 1);
		
		return maxIdUser + 1;
	}
	
}
