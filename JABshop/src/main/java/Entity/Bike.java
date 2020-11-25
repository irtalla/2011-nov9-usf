package Entity;

public class Bike {
	
	private Integer id;
	private String name;
	private Brand brand;
	private Status status;
	
	
	public Bike(){
		id = 0;
		name = "";
		brand = new Brand();
		status = new Status();
	}

	public Integer getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public Brand getBrand() {
		return brand;
	}
	public Status getStatus() {
		return status;
	}
	
	
	public void setId(Integer id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bike other = (Bike) obj;
		if (brand == null) {
			if (other.brand != null)
				return false;
		} else if (!brand.equals(other.brand))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

	
}
