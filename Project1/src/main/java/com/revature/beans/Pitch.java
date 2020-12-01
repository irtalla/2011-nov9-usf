package com.revature.beans;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;


public class Pitch {
	
	private Integer id;
	private String title;
	private String tagLine;
	private Genre genre;
	private Form form; 
	private Status status;  
	private Date completionDate; 
	
	private Integer authorId; 
	private Integer draftId;
	private Integer genreComitteeId; 
	private Set<Integer> attachmentsIds = new HashSet<Integer>(); 
	
	
	
	public Pitch() {
		
		this.id = 0;
		this.title = "generic pitch";
		this.status = new Status();
		this.status.setId(1);
		this.status.setName("available");
		this.genre = new Genre(); 
		this.getGenre().setId(1);
		this.genre.setName("unspecified");
		
		this.form = new Form(); 
		
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



	public String getTagLine() {
		return tagLine;
	}



	public void setTagLine(String tagLine) {
		this.tagLine = tagLine;
	}



	public Genre getGenre() {
		return genre;
	}



	public void setGenre(Genre genre) {
		this.genre = genre;
	}



	public Form getForm() {
		return form;
	}



	public void setForm(Form form) {
		this.form = form;
	}



	public Status getStatus() {
		return status;
	}



	public void setStatus(Status status) {
		this.status = status;
	}



	public Date getCompletionDate() {
		return completionDate;
	}



	public void setCompletionDate(Date completionDate) {
		this.completionDate = completionDate;
	}



	public Integer getAuthorId() {
		return authorId;
	}



	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}



	public Integer getDraftId() {
		return draftId;
	}



	public void setDraftId(Integer draftId) {
		this.draftId = draftId;
	}



	public Integer getGenreComitteeId() {
		return genreComitteeId;
	}



	public void setGenreComitteeId(Integer genreComitteeId) {
		this.genreComitteeId = genreComitteeId;
	}



	public Set<Integer> getAttachmentsIds() {
		return attachmentsIds;
	}



	public void setAttachmentsIds(Set<Integer> attachmentsIds) {
		this.attachmentsIds = attachmentsIds;
	}
	
	
	
//	@Override
//	public String toString() {
//		return String.format(
//				  "id: %d\n"
//				+ "name: %s\n"
//				+ "price: %f\n"
//				+ "status: %s\n"
//				+ "category: %s\n",
//				id, name, price, status.getName(), genre.getName()
//				);
//	}
}
