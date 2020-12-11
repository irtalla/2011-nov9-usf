package dev.warrington.beans;

public class Request {

	private Integer id;
	private Story story;
	private Person requester;
	private Person requestee;
	private String information;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public Story getStory() {
		return story;
	}

	public void setStory(Story story) {
		this.story = story;
	}

	public Person getRequester() {
		return requester;
	}

	public void setRequester(Person requester) {
		this.requester = requester;
	}

	public Person getRequestee() {
		return requestee;
	}

	public void setRequestee(Person requestee) {
		this.requestee = requestee;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}
	
}