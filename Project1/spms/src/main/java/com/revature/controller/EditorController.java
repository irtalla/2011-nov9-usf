package com.revature.controller;

import com.revature.beans.Approval;
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

    }

}
