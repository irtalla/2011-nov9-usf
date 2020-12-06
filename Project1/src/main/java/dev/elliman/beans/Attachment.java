package dev.elliman.beans;

import java.io.File;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Attachment {
	@Id
	private Integer id;
	@OneToMany(fetch=FetchType.EAGER)
	@JoinColumn(name="claim")
	private Claim claimID;
	private File file;
	
	public Attachment() {
		id = null;
		claimID = null;
		file = null;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Claim getClaimID() {
		return claimID;
	}

	public void setClaimID(Claim claimID) {
		this.claimID = claimID;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((claimID == null) ? 0 : claimID.hashCode());
		result = prime * result + ((file == null) ? 0 : file.hashCode());
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
		Attachment other = (Attachment) obj;
		if (claimID == null) {
			if (other.claimID != null)
				return false;
		} else if (!claimID.equals(other.claimID))
			return false;
		if (file == null) {
			if (other.file != null)
				return false;
		} else if (!file.equals(other.file))
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
		return "Atachment [id=" + id + ", claimID=" + claimID + ", file=" + file + "]";
	}
	
	
}
