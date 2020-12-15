package com.revature.beans;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="proposed_works")
public class Work {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="proposed_work_id")
	private int id;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="creator_id")
	private Author author;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="genre")
	private Genre genre;

	@Column(name="tentative_title")
	private String title;
	
	@Column(name="tentative_completion_date")
	private Date tentativeCompletionDate;
	
	private String description;
	
	@ManyToOne
	@JoinColumn(name="work_length_type_id")
	private LengthOfWork lengthOfWork;
	
	public Work() {
		id = 0;
		author = null;
		genre = null;
		title = "";
		tentativeCompletionDate = null;
		description = "";
	}
	
	public LengthOfWork getLengthOfWork() {
		return lengthOfWork;
	}

	public void setLengthOfWork(LengthOfWork lengthOfWork) {
		this.lengthOfWork = lengthOfWork;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the author
	 */
	public Author getAuthor() {
		return author;
	}

	/**
	 * @param author the author to set
	 */
	public void setAuthor(Author author) {
		this.author = author;
	}

	/**
	 * @return the genre
	 */
	public Genre getGenre() {
		return genre;
	}

	/**
	 * @param genre the genre to set
	 */
	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the tentativeCompletionDate
	 */
	public Date getTentativeCompletionDate() {
		return tentativeCompletionDate;
	}

	/**
	 * @param tentativeCompletionDate the tentativeCompletionDate to set
	 */
	public void setTentativeCompletionDate(Date tentativeCompletionDate) {
		this.tentativeCompletionDate = tentativeCompletionDate;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "The proposed literary work " + title + " by " + (author != null ? author.getName() : "N/A")
			+ "\nSummary: " + description + "\nThis literary work is a " + (lengthOfWork != null ? lengthOfWork.getName() : "N/A")
			+ "\nGenre: " + (genre != null ? genre.getGenreName() : "N/A");
	}

	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((genre == null) ? 0 : genre.hashCode());
		result = prime * result + id;
		result = prime * result + ((lengthOfWork == null) ? 0 : lengthOfWork.hashCode());
		result = prime * result + ((tentativeCompletionDate == null) ? 0 : tentativeCompletionDate.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		Work other = (Work) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (genre == null) {
			if (other.genre != null)
				return false;
		} else if (!genre.equals(other.genre))
			return false;
		if (id != other.id)
			return false;
		if (lengthOfWork == null) {
			if (other.lengthOfWork != null)
				return false;
		} else if (!lengthOfWork.equals(other.lengthOfWork))
			return false;
		if (tentativeCompletionDate == null) {
			if (other.tentativeCompletionDate != null)
				return false;
		} else if (!tentativeCompletionDate.equals(other.tentativeCompletionDate))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
	
}
