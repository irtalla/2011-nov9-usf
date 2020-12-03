package dev.elliman.beans;

public class Event {
	private Integer id;
	private String eventType;
	private Double percentageCovered;
	
	public Event() {
		id = null;
		eventType = null;
		percentageCovered = null;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public Double getPercentageCovered() {
		return percentageCovered;
	}

	public void setPercentageCovered(Double percentageCovered) {
		this.percentageCovered = percentageCovered;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((eventType == null) ? 0 : eventType.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((percentageCovered == null) ? 0 : percentageCovered.hashCode());
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
		Event other = (Event) obj;
		if (eventType == null) {
			if (other.eventType != null)
				return false;
		} else if (!eventType.equals(other.eventType))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (percentageCovered == null) {
			if (other.percentageCovered != null)
				return false;
		} else if (!percentageCovered.equals(other.percentageCovered))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Event [id=" + id + ", eventType=" + eventType + ", percentageCovered=" + percentageCovered + "]";
	}

	
}
