package models;

public class Issue {
	private int id;
	private String problem;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProblem() {
		return problem;
	}
	public void setProblem(String problem) {
		this.problem = problem;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((problem == null) ? 0 : problem.hashCode());
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
		Issue other = (Issue) obj;
		if (id != other.id)
			return false;
		if (problem == null) {
			if (other.problem != null)
				return false;
		} else if (!problem.equals(other.problem))
			return false;
		return true;
	}
	
}
