package Entity;

public class EventType {
	
	private int id;
	private String name;
	private double por;
	private ApproveType appr;
	private int cost;


	public EventType() {
		id = 0;
		name = "";
		por = 0.0;
		appr = new ApproveType();
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


	public double getPor() {
		return por;
	}


	public void setPor(double por) {
		this.por = por;
	}


	public ApproveType getAppr() {
		return appr;
	}


	public void setAppr(ApproveType appr) {
		this.appr = appr;
	}


	public int getCost() {
		return cost;
	}


	public void setCost(int cost) {
		this.cost = cost;
	}
	
	
	
}