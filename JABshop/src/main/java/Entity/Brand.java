package Entity;

public class Brand {
	
	private Integer id;
	private String bname;
	
	
	public Brand() {
		id = 0;
		bname = "";
	}
	
	
	public Integer getId() {
		return id;
	}
	public String getBname() {
		return bname;
	}
	
	
	public void setId(Integer id) {
		this.id = id;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}

}
