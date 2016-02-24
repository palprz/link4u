package controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;

import service.DbUser;

@Transactional(readOnly = true)
public class SecurityController implements UserDetailsService {
	
	private static Logger log = Logger.getLogger(SecurityController.class);
	
	private DbUserDAO userDAO = new DbUserDAO();
	
	/**
	 * Get data about user by username
	 * @return User 
	 */
	public UserDetails loadUserByUsername(String username) {
		DbUser dbUser = userDAO.searchDatabase(username);
		
		if (dbUser == null) {
			log.debug("Return dbUser with null value!");
			return null;
		}
		
		return new User(dbUser.getUsername(), dbUser.getPassword().toLowerCase(),
				true, true, true, true, getAuthoritie());
	}
	
	/**
	 * Method for creating User type with raw password for creating new session after correctly sign up
	 * @param dbUser
	 * @return User
	 */
	public UserDetails initUserWithRawPassword(DbUser dbUser) {
		return new User(dbUser.getUsername(), dbUser.getPassword().toLowerCase(),
				true, true, true, true, getAuthoritie());
	}
	
 	/**
 	 * For now there isn't parameter by in future here could be implement adding new role: ROLE_ADMIN
 	 * @return auths - for now only ROLE_USER
 	 */
	public Collection<GrantedAuthority> getAuthoritie() {
		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(1);
		authList.add(new GrantedAuthorityImpl("ROLE_USER"));
		return authList;
	}
	
}
