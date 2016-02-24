package service;

public class Link implements IPojo {

	private int id;
	private String address;
	private String description;
	private int idGenre;
	private int idUser;
	
	public Link() {
		
	}
	
	public Link(int id, String address, String description, int idUser, int idGenre) {
		this.id = id;
		this.address = address;
		this.description = description;
		this.idUser = idUser;
		this.idGenre = idGenre;		
	}
	
	public Link(Link other) {
		this.id = other.id;
		this.address = other.address;
		this.description = other.description;
		this.idUser = other.idUser;
		this.idGenre = other.idGenre;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public int getIdGenre() {
		return idGenre;
	}
	
	public void setIdGenre(int idGenre) {
		this.idGenre = idGenre;
	}
	
	public int getIdUser() {
		return idUser;
	}
	
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	
}
