package service;

public class Genre implements IPojo {
	
	private int id;
	private String name;
	private int idUser;
	
	public Genre() {
		
	}
	
	public Genre(int id, String name, int idUser) {
		this.id = id;
		this.name = name;
		this.idUser = idUser;
	}
	
	public Genre(Genre other) {
		this.id = other.id;
		this.name = other.name;
		this.idUser = other.idUser;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getIDUser() {
		return idUser;
	}
	
	public void setIDUser(int idUser) {
		this.idUser = idUser;
	}
	
}
