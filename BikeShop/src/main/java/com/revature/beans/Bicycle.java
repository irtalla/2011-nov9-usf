package com.revature.beans;

import java.util.HashSet;
import java.util.Set;

public class Bicycle extends ForSale {
	//offer IDS
	private String model;
	private String brand;
	private Set<Offer> offers;
	
	public Bicycle()
	{
		super(-1,0f);
		model = "blank";
		brand = "blank";
		offers = new HashSet<>();
	}
	
	public Bicycle(Integer ID, Float price, Offer... offers)
	{
		super(ID, price);
		this.offers = new HashSet<Offer>();
		for(Offer o: offers)
		{
			this.offers.add(o);
		}
	}
	
	public Bicycle(Integer ID, Float price, Set<Offer> offers)
	{
		super(ID, price);
		this.offers = offers;
	}

	public Set<Offer> getOffers() {
		return offers;
	}

	public void setOffers(Set<Offer> offers) {
		this.offers = offers;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public boolean isOwned()
	{
		for (Offer o: offers)
		{
			if (o.getStatus().getID() == 2)
				return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((brand == null) ? 0 : brand.hashCode());
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + ((offers == null) ? 0 : offers.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bicycle other = (Bicycle) obj;
		if (brand == null) {
			if (other.brand != null)
				return false;
		} else if (!brand.equals(other.brand))
			return false;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		if (offers == null) {
			if (other.offers != null)
				return false;
		} else if (!offers.equals(other.offers))
			return false;
		return true;
	}

	
	
	
}
