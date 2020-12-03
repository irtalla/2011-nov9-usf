package com.revature.beans;

//import java.util.HashSet;
import java.util.Set;
import java.util.Date;

public class Pitch {
	private Integer id;
	private String title;
	private String description;
	private String tagline;
	private Genre genre;
	private StoryType storytype;
	private Date date;
	private Priority priority;
	private ReviewStatus reviewstatus;
	private ReviewStage reviewstage;
	
	public Pitch() {
		id = 0;
		title = "";
		description = "";
		tagline = "";
		genre = new Genre();
		storytype = new StoryType();
		date = null;
		priority = new Priority();
		reviewstatus = new ReviewStatus();
		reviewstage = new ReviewStage();
		
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTagline() {
		return tagline;
	}

	public void setTagline(String tagline) {
		this.tagline = tagline;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public StoryType getStorytype() {
		return storytype;
	}

	public void setStorytype(StoryType storytype) {
		this.storytype = storytype;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public ReviewStatus getReviewstatus() {
		return reviewstatus;
	}

	public void setReviewstatus(ReviewStatus reviewstatus) {
		this.reviewstatus = reviewstatus;
	}

	public ReviewStage getReviewstage() {
		return reviewstage;
	}

	public void setReviewstage(ReviewStage reviewstage) {
		this.reviewstage = reviewstage;
	}
}
