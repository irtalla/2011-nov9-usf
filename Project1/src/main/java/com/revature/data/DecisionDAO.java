package com.revature.data;

import java.util.Set;

import com.cross.beans.Comment;
import com.cross.beans.Decision;

public interface DecisionDAO {

	Decision getById(Integer id);

	Set<Decision> getAll();

	boolean update(Decision t);

	boolean delete(Decision t);

	Decision add(Decision c);

	Set<Comment> getByEditorId(Integer editorId);

	Set<Comment> getByPitchId(Integer pitchId);

}
