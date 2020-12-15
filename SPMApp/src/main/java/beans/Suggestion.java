package beans;
import javax.persistence.*;

@Entity
@Table(name="suggestion")
public class Suggestion {
	public Suggestion() {
		suggestion_id = 0;
		pitch = new Pitch();
		contents = "";
//		author = new Usr();
	}

	public Integer getSuggestion_id() {
		return suggestion_id;
	}

	public void setSuggestion_id(Integer suggestion_id) {
		this.suggestion_id = suggestion_id;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public Pitch getPitch() {
		return pitch;
	}

	public void setPitch(Pitch pitch) {
		this.pitch = pitch;
	}

	@Override
	public String toString() {
		return "Suggestion [suggestion_id=" + suggestion_id + ", contents=" + contents + ", pitch=" + pitch + "]";
	}

	@Id
	@Column(name="suggestion_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer suggestion_id;

	@Column(name="contents")
	private String contents;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="pitch_id")
	private Pitch pitch;
}