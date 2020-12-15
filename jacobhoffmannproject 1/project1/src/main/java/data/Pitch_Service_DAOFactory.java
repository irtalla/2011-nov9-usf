package data;

public class Pitch_Service_DAOFactory {
	public Pitch_StatusDAO getDao() {
		return new Pitch_StatusHibernate();
	}
}
