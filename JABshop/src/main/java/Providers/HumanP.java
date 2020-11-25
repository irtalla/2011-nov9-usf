package Providers;

import Entity.Human;

public interface HumanP {
	public Integer addHuman(Human h); //throws NonUniqueUsernameException;
	public Human getHumanById(Integer id);
	public Human getHumanByUsername(String username);
	public void updateHuman(Human h);
	public void removeHuman(Human h);
}
