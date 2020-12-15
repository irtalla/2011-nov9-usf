package data;

import models.Editor;

public interface Editor_Dao {
		public Editor getEditorById(Integer id);
		public Editor getByEmployeeId(Integer i);
		public Editor add(Editor e);
		public void updateEditor(Editor e);
}
