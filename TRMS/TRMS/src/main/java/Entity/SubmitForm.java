package Entity;

public class SubmitForm {
	
	private Employee emp;
	private EventType et;
	private Status stat;
	private String date;
	private String description;
	private String grade;
	private String additionalInfo;

	public SubmitForm() {
		emp = new Employee();
		et = new EventType();
		stat = new Status();
		date = "";
		grade = "n/a";
		description = "";
		additionalInfo = "";
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public Employee getEmp() {
		return emp;
	}

	public void setEmp(Employee emp) {
		this.emp = emp;
	}

	public EventType getEt() {
		return et;
	}

	public void setEt(EventType et) {
		this.et = et;
	}

	public Status getStat() {
		return stat;
	}

	public void setStat(Status stat) {
		this.stat = stat;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

}
