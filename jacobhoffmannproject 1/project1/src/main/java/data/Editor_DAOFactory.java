package data;

public class Editor_DAOFactory {
	public Editor_Dao getEditorDao() {
		return new Editor_Hibernate();
	}
}
