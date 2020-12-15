package beans;
import javax.persistence.*;
@Entity
@Table(name="status")
public class Status {
@Id
private int status_id;
public Status() {
	this.status_id = 1;
	this.status_name = "";
}
@Override
public String toString() {
	return "Status [status_id=" + status_id + ", status_name=" + status_name + "]";
}
public Integer getStatus_id() {
	return status_id;
}
public void setStatus_id(Integer status_id) {
	this.status_id = status_id;
}
public String getStatus_name() {
	return status_name;
}
public void setStatus_name(String status_name) {
	this.status_name = status_name;
}
private String status_name;
}
