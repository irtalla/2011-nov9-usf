package dev.elliman.beans;

import java.time.LocalDateTime;

public class Claim {
	private Integer id;
	private Integer personID;
	private Integer eventID;
	private Integer gradingID;
	private LocalDateTime eventDate;
	private String eventLocation;
	private String description;
	private Double price;
	private String justification;
	private Integer hoursMissed;
	private Integer approvalStage;
	private Integer dsaID;
	private Integer dhaID;
	private Integer bcaID;
	private String denialReason;
	
	public Claim() {
		id = null;
		personID = null;
		eventID = null;
		gradingID = null;
		eventDate = null;
		eventLocation = null;
		description = null;
		price = null;
		justification = null;
		hoursMissed = null;
		approvalStage = null;
		dsaID = null;
		dhaID = null;
		bcaID = null;
		denialReason = null;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPersonID() {
		return personID;
	}

	public void setPersonID(Integer personID) {
		this.personID = personID;
	}

	public Integer getEventID() {
		return eventID;
	}

	public void setEventID(Integer eventID) {
		this.eventID = eventID;
	}

	public Integer getGradingID() {
		return gradingID;
	}

	public void setGradingID(Integer gradingID) {
		this.gradingID = gradingID;
	}

	public LocalDateTime getEventDate() {
		return eventDate;
	}

	public void setEventDate(LocalDateTime eventDate) {
		this.eventDate = eventDate;
	}

	public String getEventLocation() {
		return eventLocation;
	}

	public void setEventLocation(String eventLocation) {
		this.eventLocation = eventLocation;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getJustification() {
		return justification;
	}

	public void setJustification(String justification) {
		this.justification = justification;
	}

	public Integer getHoursMissed() {
		return hoursMissed;
	}

	public void setHoursMissed(Integer hoursMissed) {
		this.hoursMissed = hoursMissed;
	}

	public Integer getApprovalStage() {
		return approvalStage;
	}

	public void setApprovalStage(Integer approvalStage) {
		this.approvalStage = approvalStage;
	}

	public Integer getDsaID() {
		return dsaID;
	}

	public void setDsaID(Integer dsaID) {
		this.dsaID = dsaID;
	}

	public Integer getDhaID() {
		return dhaID;
	}

	public void setDhaID(Integer dhaID) {
		this.dhaID = dhaID;
	}

	public Integer getBcaID() {
		return bcaID;
	}

	public void setBcaID(Integer bcaID) {
		this.bcaID = bcaID;
	}

	public String getDenialReason() {
		return denialReason;
	}

	public void setDenialReason(String denialReason) {
		this.denialReason = denialReason;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((approvalStage == null) ? 0 : approvalStage.hashCode());
		result = prime * result + ((bcaID == null) ? 0 : bcaID.hashCode());
		result = prime * result + ((denialReason == null) ? 0 : denialReason.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((dhaID == null) ? 0 : dhaID.hashCode());
		result = prime * result + ((dsaID == null) ? 0 : dsaID.hashCode());
		result = prime * result + ((eventDate == null) ? 0 : eventDate.hashCode());
		result = prime * result + ((eventID == null) ? 0 : eventID.hashCode());
		result = prime * result + ((eventLocation == null) ? 0 : eventLocation.hashCode());
		result = prime * result + ((gradingID == null) ? 0 : gradingID.hashCode());
		result = prime * result + ((hoursMissed == null) ? 0 : hoursMissed.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((justification == null) ? 0 : justification.hashCode());
		result = prime * result + ((personID == null) ? 0 : personID.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
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
		Claim other = (Claim) obj;
		if (approvalStage == null) {
			if (other.approvalStage != null)
				return false;
		} else if (!approvalStage.equals(other.approvalStage))
			return false;
		if (bcaID == null) {
			if (other.bcaID != null)
				return false;
		} else if (!bcaID.equals(other.bcaID))
			return false;
		if (denialReason == null) {
			if (other.denialReason != null)
				return false;
		} else if (!denialReason.equals(other.denialReason))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (dhaID == null) {
			if (other.dhaID != null)
				return false;
		} else if (!dhaID.equals(other.dhaID))
			return false;
		if (dsaID == null) {
			if (other.dsaID != null)
				return false;
		} else if (!dsaID.equals(other.dsaID))
			return false;
		if (eventDate == null) {
			if (other.eventDate != null)
				return false;
		} else if (!eventDate.equals(other.eventDate))
			return false;
		if (eventID == null) {
			if (other.eventID != null)
				return false;
		} else if (!eventID.equals(other.eventID))
			return false;
		if (eventLocation == null) {
			if (other.eventLocation != null)
				return false;
		} else if (!eventLocation.equals(other.eventLocation))
			return false;
		if (gradingID == null) {
			if (other.gradingID != null)
				return false;
		} else if (!gradingID.equals(other.gradingID))
			return false;
		if (hoursMissed == null) {
			if (other.hoursMissed != null)
				return false;
		} else if (!hoursMissed.equals(other.hoursMissed))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (justification == null) {
			if (other.justification != null)
				return false;
		} else if (!justification.equals(other.justification))
			return false;
		if (personID == null) {
			if (other.personID != null)
				return false;
		} else if (!personID.equals(other.personID))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Claim [id=" + id + ", personID=" + personID + ", eventID=" + eventID + ", gradingID=" + gradingID
				+ ", eventDate=" + eventDate + ", eventLocation=" + eventLocation + ", description=" + description
				+ ", price=" + price + ", justification=" + justification + ", hoursMissed=" + hoursMissed
				+ ", approvalStage=" + approvalStage + ", dsaID=" + dsaID + ", dhaID=" + dhaID + ", bcaID=" + bcaID
				+ ", denialReason=" + denialReason + "]";
	}
	
	
}
