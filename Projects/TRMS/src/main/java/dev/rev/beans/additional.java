package dev.rev.beans;

public class additional {

		private int add_id;
		private int form_id;
		private String add_info;
		
		public additional() {
			
			add_id=form_id=0;
			add_info="";
		}

		public int getAdd_id() {
			return add_id;
		}

		public void setAdd_id(int add_id) {
			this.add_id = add_id;
		}

		public int getForm_id() {
			return form_id;
		}

		public void setForm_id(int form_id) {
			this.form_id = form_id;
		}

		public String getAdd_info() {
			return add_info;
		}

		public void setAdd_info(String add_info) {
			this.add_info = add_info;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + add_id;
			result = prime * result + ((add_info == null) ? 0 : add_info.hashCode());
			result = prime * result + form_id;
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
			additional other = (additional) obj;
			if (add_id != other.add_id)
				return false;
			if (add_info == null) {
				if (other.add_info != null)
					return false;
			} else if (!add_info.equals(other.add_info))
				return false;
			if (form_id != other.form_id)
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "additional [add_id=" + add_id + ", form_id=" + form_id + ", add_info=" + add_info + "]";
		}
		
		
}
