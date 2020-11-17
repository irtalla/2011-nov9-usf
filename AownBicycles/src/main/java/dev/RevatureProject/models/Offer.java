package dev.RevatureProject.models;

public class Offer {
		
	private int id;
	private int bikeid;
	private int price;
	private Person person;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBikeid() {
		return bikeid;
	}
	public void setBikeid(int bikeid) {
		this.bikeid = bikeid;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public Offer(int id, int bikeid, int price, Person person) {
		super();
		this.id = id;
		this.bikeid = bikeid;
		this.price = price;
		this.person = person;
	}
	@Override
	public String toString() {
		return "Offer [id=" + id + ", bikeid=" + bikeid + ", price=" + price + ", person=" + person + "]";
	}
	
	
}
