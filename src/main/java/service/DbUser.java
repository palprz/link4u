package service;

public class DbUser implements IPojo {

	private int id;
	private String username;
	private String password;
	
	public DbUser() {
		
	}
	
	public DbUser(int id, String username, String password) {
		this.id = id;
		this.username = username;
		this.password = password;		
	}

	public DbUser(DbUser other) {
		this.id = other.id;
		this.username = other.username;
		this.password = other.password;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "User [id: " + id + "] username: " + username + ", password: " + password;
	}
	
}
