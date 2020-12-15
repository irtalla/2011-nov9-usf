package servicespackage.editor;

import data.Editor_DAOFactory;
import data.Editor_Dao;
import models.Editor;

public class Editor_ServiceImpl implements Editor_Service{
	private Editor_Dao eddao;
	public Editor_ServiceImpl() {
		Editor_DAOFactory eddaofactory = new Editor_DAOFactory();
		eddao = eddaofactory.getEditorDao();
	}
	public Integer addEditor(Editor e) {
		return eddao.add(e).getId();
	}
	public Editor getByEditorId(Integer i) {
			return eddao.getEditorById(i);
	}
	public Editor getByEmployeeId(Integer i) {
		return eddao.getByEmployeeId(i);
	}
	public void updateEditor(Editor e) {
		eddao.updateEditor(e);
	}
}
