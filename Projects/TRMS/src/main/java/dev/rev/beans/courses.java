package dev.rev.beans;

public class courses {

		private int course_id ;
		private String course_name;
		private int emp_id;
		
		public courses() {
			course_id=0;
			emp_id=0;
			course_name="";
		}
		
		public int getCourse_id() {
			return course_id;
		}
		public void setCourse_id(int course_id) {
			this.course_id = course_id;
		}
		public String getCourse_name() {
			return course_name;
		}
		public void setCourse_name(String course_name) {
			this.course_name = course_name;
		}
		public int getEmp_id() {
			return emp_id;
		}
		public void setEmp_id(int emp_id) {
			this.emp_id = emp_id;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + course_id;
			result = prime * result + ((course_name == null) ? 0 : course_name.hashCode());
			result = prime * result + emp_id;
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
			courses other = (courses) obj;
			if (course_id != other.course_id)
				return false;
			if (course_name == null) {
				if (other.course_name != null)
					return false;
			} else if (!course_name.equals(other.course_name))
				return false;
			if (emp_id != other.emp_id)
				return false;
			return true;
		}
		@Override
		public String toString() {
			return "courses [course_id=" + course_id + ", course_name=" + course_name + ", emp_id=" + emp_id + "]";
		}
		
		
}
