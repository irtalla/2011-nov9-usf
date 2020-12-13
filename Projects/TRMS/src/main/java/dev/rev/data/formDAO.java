package dev.rev.data;

import java.util.List;

import dev.rev.beans.reimbForm;

public interface formDAO extends genericDAO<reimbForm>{

		public List<reimbForm> getempforms(int emp_id); 
}
