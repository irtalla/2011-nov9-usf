package com.revature.dao;

import java.util.List;

import com.revature.beans.Editor;
import com.revature.beans.Genre;
import com.revature.beans.GenreCommitteeMember;
import com.revature.beans.Work;

public interface GenreCommitteeMemberDAO {
	public Editor assignAnAssistantEditor(Work w);
	public Editor assignEditorNotInGenre(Work w);
	public Editor assignSeniorEditor(Work w);
	public List<GenreCommitteeMember> getAllEditorsInAGenre(Work w);
}
