package com.revature.controller;

import com.revature.beans.Approval;
import com.revature.beans.Editor;
import com.revature.beans.User;
import com.revature.data.ApprovalHibernate;
import com.revature.data.AuthorHibernate;
import com.revature.data.EditorHibernate;
import com.revature.data.UserHibernate;
import io.javalin.http.Context;

import java.util.Set;


public class EditorController {
    private static UserHibernate userHibernate = new UserHibernate();
    private static AuthorHibernate authorHibernate = new AuthorHibernate();
    private static EditorHibernate editorHibernate = new EditorHibernate();
    private static ApprovalHibernate approvalHibernate = new ApprovalHibernate();

    public static void generateApprovalList(Context ctx){
        User user = ctx.sessionAttribute("user");
        Editor editor = new Editor();

        Set<Editor> editorSet = editorHibernate.getAll();
        for (Editor e : editorSet){
            if (e.getUser().getId() == user.getId()){
                editor = e;
            }
        }

        Set<Approval> approvalSet = approvalHibernate.getApprovalByEditor(editor.getId());
        ctx.status(200);
        ctx.json(approvalSet);
    }

}
