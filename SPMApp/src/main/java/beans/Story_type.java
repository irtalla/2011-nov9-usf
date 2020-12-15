package beans;
import javax.persistence.*;
@Entity
@Table(name="story_type")
public class Story_type {
	@Override
	public String toString() {
		return "Story_type [type_id=" + type_id + ", type_name=" + type_name + "]";
	}
	public Story_type() {
		this.type_id = 1;
		this.type_name = "";
	}
	@Id
	private Integer type_id;
	public Integer getType_id() {
		return type_id;
	}
	public void setType_id(Integer type_id) {
		this.type_id = type_id;
	}
	public String getType_name() {
		return type_name;
	}
	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
	private String type_name;
}
