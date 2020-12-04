package com.revature.beans;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;


public class Pitch {
	
	
	
//	id serial primary key,
//	title varchar(40),
//	tag_line varchar(1000),
//	status_id integer references status,
//	genre_id integer references genre,
//	form_id integer references form, 
//	author_id integer references person,
//	general_editor_id integer references person,
//	priority_lvl_id integer references priority,
//	stage_id integer references stage,
//	deadline date, 
//	createdTime date, 
//	lastModifiedTime date
	
	private Integer id;
	private Integer authorId; 
	private Integer generalEditorId; 
	private String title;
	private String tagLine;
	private Status status;  
	private Genre genre;
	private Form form; 
	private Stage stage; 
	private PriorityLevel priorityLevel; 
	private Date completionDate; 
	
	private Integer draftId;
	private Integer genreComitteeId; 
	private Set<Integer> attachmentsIds = new HashSet<Integer>();
	
	public Pitch() {}; 
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getAuthorId() {
		return authorId;
	}
	
	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}
	
	public Integer getGeneralEditorId() {
		return generalEditorId;
	}
	
	public void setGeneralEditorId(Integer generalEditorId) {
		this.generalEditorId = generalEditorId;
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
	
	public Status getStatus() {
		return status;
	}
	
	public void setStatus(Status status) {
		this.status = status;
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
	
	public Stage getStage() {
		return stage;
	}
	
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	public PriorityLevel getPriorityLevel() {
		return priorityLevel;
	}
	
	public void setPriorityLevel(PriorityLevel priorityLevel) {
		this.priorityLevel = priorityLevel;
	}
	
	public Date getCompletionDate() {
		return completionDate;
	}
	
	public void setCompletionDate(Date completionDate) {
		this.completionDate = completionDate;
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
