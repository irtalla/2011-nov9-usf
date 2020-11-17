package dev.RevatureProject.models;

public class Bike {
	
	private String BikeBrand;
	private int Bikeid;
	private int price;
	private String model;
	public String getBikeBrand() {
		return BikeBrand;
	}
	public void setBikeBrand(String bikeBrand) {
		BikeBrand = bikeBrand;
	}
	public int getBikeid() {
		return Bikeid;
	}
	public void setBikeid(int bikeid) {
		Bikeid = bikeid;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((BikeBrand == null) ? 0 : BikeBrand.hashCode());
		result = prime * result + Bikeid;
		result = prime * result + ((model == null) ? 0 : model.hashCode());
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
		Bike other = (Bike) obj;
		if (BikeBrand == null) {
			if (other.BikeBrand != null)
				return false;
		} else if (!BikeBrand.equals(other.BikeBrand))
			return false;
		if (Bikeid != other.Bikeid)
			return false;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		if (price != other.price)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Bike [BikeBrand=" + BikeBrand + ", Bikeid=" + Bikeid + ", price=" + price + ", model=" + model + "]";
	}
	public Bike(String bikeBrand, int bikeid, int price, String model) {
		super();
		BikeBrand = bikeBrand;
		Bikeid = bikeid;
		this.price = price;
		this.model = model;
	}
	
	
	
	

}
