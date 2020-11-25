package models;


public class Bike {
	private int Id;
	
	private String Color;
	private String Size;
	private Model Modeltype;
	private Status Status;
	
	
	public Bike() {
		super();
	}
	public Bike(int id, String color, String size, Model modeltype, models.Status status) {
		super();
		Id = id;
		Color = color;
		Size = size;
		Modeltype = modeltype;
		Status = status;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getColor() {
		return Color;
	}
	public void setColor(String color) {
		Color = color;
	}
	public String getSize() {
		return Size;
	}
	public void setSize(String size) {
		Size = size;
	}
	public Model getModeltype() {
		return Modeltype;
	}
	public void setModeltype(Model modeltype) {
		Modeltype = modeltype;
	}
	public Status getStatus() {
		return Status;
	}
	public void setStatus(Status status) {
		Status = status;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Color == null) ? 0 : Color.hashCode());
		result = prime * result + Id;
		result = prime * result + ((Modeltype == null) ? 0 : Modeltype.hashCode());
		result = prime * result + ((Size == null) ? 0 : Size.hashCode());
		result = prime * result + ((Status == null) ? 0 : Status.hashCode());
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
		if (Color == null) {
			if (other.Color != null)
				return false;
		} else if (!Color.equals(other.Color))
			return false;
		if (Id != other.Id)
			return false;
		if (Modeltype == null) {
			if (other.Modeltype != null)
				return false;
		} else if (!Modeltype.equals(other.Modeltype))
			return false;
		if (Size == null) {
			if (other.Size != null)
				return false;
		} else if (!Size.equals(other.Size))
			return false;
		if (Status == null) {
			if (other.Status != null)
				return false;
		} else if (!Status.equals(other.Status))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Bike [Id=" + Id + ", Color=" + Color + ", Size=" + Size + ", Modeltype=" + Modeltype + ", Status="
				+ Status + "]";
	}
	
	
	
}
