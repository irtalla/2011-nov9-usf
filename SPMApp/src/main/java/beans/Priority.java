package beans;
import javax.persistence.*;

@Entity
@Table(name="priorities")
public class Priority {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int level_id;
	@Override
	public String toString() {
		return "Priority [level_id=" + level_id + ", p_level=" + p_level + "]";
	}
	public Priority() {
		this.level_id = 1;
		this.p_level = "standard";
	}
	private String p_level;
	public Integer getLevel_id() {
		return level_id;
	}
	public void setLevel_id(Integer level_id) {
		this.level_id = level_id;
	}
	public String getP_level() {
		return p_level;
	}
	public void setP_level(String p_level) {
		this.p_level = p_level;
	}
}
