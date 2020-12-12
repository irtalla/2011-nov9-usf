package com.revature.controller;

import com.revature.data.AuthorHibernate;
import com.revature.data.EditorHibernate;
import com.revature.data.UserHibernate;

import javax.naming.Context;

public class AuthorController {
    private static UserHibernate userHibernate = new UserHibernate();
    private static AuthorHibernate authorHibernate = new AuthorHibernate();
    private static EditorHibernate editorHibernate = new EditorHibernate();

    public static void generateStoryList(Context ctx){

    }
}
