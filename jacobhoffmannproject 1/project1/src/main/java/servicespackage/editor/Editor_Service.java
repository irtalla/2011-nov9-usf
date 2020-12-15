package servicespackage.editor;

import models.Editor;

public interface Editor_Service{
	public Integer addEditor(Editor e);
	public Editor getByEditorId(Integer i);
	public Editor getByEmployeeId(Integer i);
	public void updateEditor(Editor e);
}
