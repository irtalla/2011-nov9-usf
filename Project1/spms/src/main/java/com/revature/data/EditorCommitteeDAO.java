package com.revature.data;

import com.revature.beans.Committee;
import com.revature.beans.Editor;

public interface EditorCommitteeDAO {
    public Editor getEditorByCommittee();
    public Committee getCommitteeByEditor();
}
