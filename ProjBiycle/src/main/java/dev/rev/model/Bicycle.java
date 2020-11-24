package dev.rev.model;

public class Bicycle {
	
	private int id;
	private int price;
	private String brand;
	private String Color;
	private int Quantity;
	private int person_id;
	private String bicycle_status;
	
	public Bicycle() {
		person_id=0;
		bicycle_status="";
		id=0;
		price=0;
		brand="";
		Color="";
		Quantity=0;
	}

	@Override
	public String toString() {
		return "Bicycle [id=" + id + ", price=" + price + ", brand=" + brand + ", Color=" + Color + ", Quantity="
				+ Quantity + ", person_id=" + person_id + ", bicycle_status=" + bicycle_status + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Color == null) ? 0 : Color.hashCode());
		result = prime * result + Quantity;
		result = prime * result + ((bicycle_status == null) ? 0 : bicycle_status.hashCode());
		result = prime * result + ((brand == null) ? 0 : brand.hashCode());
		result = prime * result + id;
		result = prime * result + person_id;
		result = prime * result + price;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bicycle other = (Bicycle) obj;
		if (Color == null) {
			if (other.Color != null)
				return false;
		} else if (!Color.equals(other.Color))
			return false;
		if (Quantity != other.Quantity)
			return false;
		if (bicycle_status == null) {
			if (other.bicycle_status != null)
				return false;
		} else if (!bicycle_status.equals(other.bicycle_status))
			return false;
		if (brand == null) {
			if (other.brand != null)
				return false;
		} else if (!brand.equals(other.brand))
			return false;
		if (id != other.id)
			return false;
		if (person_id != other.person_id)
			return false;
		if (price != other.price)
			return false;
		return true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getColor() {
		return Color;
	}

	public void setColor(String color) {
		Color = color;
	}

	public int getQuantity() {
		return Quantity;
	}

	public void setQuantity(int quantity) {
		Quantity = quantity;
	}

	public int getPerson_id() {
		return person_id;
	}

	public void setPerson_id(int person_id) {
		this.person_id = person_id;
	}

	public String getBicycle_status() {
		return bicycle_status;
	}

	public void setBicycle_status(String bicycle_status) {
		this.bicycle_status = bicycle_status;
	}
}
