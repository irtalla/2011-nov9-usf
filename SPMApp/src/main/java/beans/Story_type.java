package beans;
import javax.persistence.*;
@Entity
@Table(name="story_type")
public class Story_type {
	@Override
	public String toString() {
		return "Story_type [type_id=" + type_id + ", typename=" + typename + "]";
	}
	public Story_type() {
		this.type_id = 1;
		this.typename = "";
	}
	@Id
	private Integer type_id;
	public Integer getType_id() {
		return type_id;
	}
	public void setType_id(Integer type_id) {
		this.type_id = type_id;
	}
	public String getTypename() {
		return typename;
	}
	public void setTypename(String type_name) {
		this.typename = type_name;
	}
	@Column(name="type_name")
	private String typename;
}
