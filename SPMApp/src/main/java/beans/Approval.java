package beans;

import javax.persistence.*;
@Entity
@Table(name="editor_pitch_approvals")
public class Approval {
	public void setApproval_Id(Integer approval_id) {
		this.approval_id = approval_id;
	}
	public Integer getApproval_id() {
		return approval_id;
	}
	public Pitch getPitch() {
		return pitch;
	}
	public void setPitch(Pitch pitch) {
		this.pitch= pitch;
	}
	public Genre getGenre() {
		return genre;
	}
	public void setGenre(Genre genre) {
		this.genre = genre;
	}
//	public Usr getAuthor() {
//		return author;
//	}
//	public void setAuthor(Usr author) {
//		this.author=author;
//	}
	public void setSuggestion(String suggestion) {
		this.suggestion=suggestion;
	}

	@Override
	public String toString() {
		return "Approval [author =" + ", pitch=" + pitch + ", genre=" + genre + "]";
	}
	public Approval() {
		approval_id = 0;
//		author = new Usr();
		pitch = new Pitch();
		genre = new Genre();
		suggestion = "";
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer approval_id;
//	@ManyToOne(fetch=FetchType.EAGER)
//	@JoinColumn(name="usr_id")
//	private Usr author;
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="app_pitch")
	private Pitch pitch;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="editor_genre")
	private Genre genre;
	@Column(name="suggestion")
	private String suggestion;
}
