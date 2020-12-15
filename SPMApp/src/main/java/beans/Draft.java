package beans;
import javax.persistence.*;

@Entity
@Table(name="drafts")
public class Draft {
	public Draft() {
		draft_id = 0;
		pitch = new Pitch();
		story_type=new Story_type();
		state = "Writeable";
		contents = "";
//		author = new Usr();
	}
	public Integer getDraft_id() {
		return draft_id;
	}
	public void setDraft_id(Integer draft_id) {
		this.draft_id = draft_id;
	}
	public Story_type getStory_type() {
		return story_type;
	}
	public void setStory_type(Story_type story_type) {
		this.story_type = story_type;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
//	public Usr getAuthor() {
//		return author;
//	}
//	public void setAuthor(Usr author) {
//		this.author = author;
//	}
	public Pitch getPitch() {
		return pitch;
	}
	public void setPitch(Pitch pitch) {
		this.pitch = pitch;
	}
	@Id
	@Column(name="draft_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer draft_id;
	@Override
	public String toString() {
		return "Draft [draft_id=" + draft_id + ", story_type=" + story_type + ", state=" + state + ", contents="
				+ contents + ", pitch=" + pitch + "]";
	}
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="story_type")
	private Story_type story_type;
	@Column(name="state")
	private String state;
	@Column(name="contents")
	private String contents;
//	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
//	@JoinColumn(name="author")
//	private Usr author;
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="pitch_id")
	private Pitch pitch;
}
