package dev.elliman.beans;

public class RFC {
	private Integer id;
	private Integer claimID;
	private Integer commenterID;
	private String description;
	
	public RFC() {
		id = null;
		claimID = null;
		commenterID = null;
		description = null;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getClaimID() {
		return claimID;
	}

	public void setClaimID(Integer claimID) {
		this.claimID = claimID;
	}

	public Integer getCommenterID() {
		return commenterID;
	}

	public void setCommenterID(Integer commenterID) {
		this.commenterID = commenterID;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((claimID == null) ? 0 : claimID.hashCode());
		result = prime * result + ((commenterID == null) ? 0 : commenterID.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		RFC other = (RFC) obj;
		if (claimID == null) {
			if (other.claimID != null)
				return false;
		} else if (!claimID.equals(other.claimID))
			return false;
		if (commenterID == null) {
			if (other.commenterID != null)
				return false;
		} else if (!commenterID.equals(other.commenterID))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RFC [id=" + id + ", claimID=" + claimID + ", commenterID=" + commenterID + ", description="
				+ description + "]";
	}
	
	
}
