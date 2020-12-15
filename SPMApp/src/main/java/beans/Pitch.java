package beans;

import java.time.LocalDateTime;

import javax.persistence.*;


@Entity
@Table(name="pitch")
public class Pitch {
	public Pitch() {
		p_id = 0;
		title = "";
		author = new Usr();
		st = new Story_type();
		priority = new Priority();
		status = new Status();
		genre = new Genre();
		description = "";
		authinfo = "";
//		genre_approval = "";
//		editor_approval = "";
//		assistant_approval = "";
	}
	@Id
	@Column(name="pitch_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer p_id;
//	@JoinColumn(name = "author_id", referencedColumnName = "usr_id")
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "author_id")
	private Usr author;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "stype_id")
	private Story_type st;
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="priority")
	private Priority priority;
	@JoinColumn(name="status_id")
	@ManyToOne(fetch=FetchType.EAGER)
	private Status status;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="genre_id")
	private Genre genre;
	@Column(name="description")
	private String description;
	@Column(name="authinfo")
	private String authinfo;
//	@Column(name="deadline")
//	private LocalDateTime deadline;
//	@Column(name="genreapproval")
//	private String genre_approval;
//	@Column(name="editorapproval")
//	private String editor_approval;
//	@Column(name="assistantapproval")
//	private String assistant_approval;
	@Column(name="title")
	private String title;
	
	@Override
	public String toString() {
		return "Pitch [p_id=" + p_id + "title=" + title + ", author_id=" + author + ", st=" + st + ", priority=" + priority + ", status="
				+ status + ", genre=" + genre + ", description=" + description + ", authinfo=" + authinfo
				+ ", genre_approval="  + ", editor_approval="
				 + ", assistant_approval=" + "]";
	}
//	public void set_assistant_approval(String approval) {
//		this.assistant_approval = approval;
//	}
//	public String getAssistantApproval() {
//		return this.assistant_approval;
//	}
	public Integer getP_id() {
		return p_id;
	}
	public void setP_id(Integer p_id) {
		this.p_id = p_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Usr getAuthor() {
		return author;
	}
	public void setAuthor(Usr u) {
		this.author = u;
	}
	public Story_type getSt() {
		return st;
	}
	public void setSt(Story_type st) {
		this.st = st;
	}
	public Priority getPriority() {
		return priority;
	}
	public void setPriority(Priority priority) {
		this.priority = priority;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAuthinfo() {
		return authinfo;
	}
	public void setAuthinfo(String authinfo) {
		this.authinfo = authinfo;
	}
//	public LocalDateTime getDeadline() {
//		return deadline;
//	}
//	public void setDeadline(LocalDateTime deadline) {
//		this.deadline = deadline;
//	}
//	public String getGenre_approval() {
//		return genre_approval;
//	}
//	public void setGenre_approval(String genre_approval) {
//		this.genre_approval = genre_approval;
//	}
//	public String getEditor_approval() {
//		return editor_approval;
//	}
//	public void setEditor_approval(String editor_approval) {
//		this.editor_approval = editor_approval;
//	}
	@Override
	public int hashCode() {
		return super.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
}
