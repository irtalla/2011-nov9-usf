package com.revature.controller;

import com.revature.beans.User;
import com.revature.service.UserService;
import io.javalin.http.Context;

public class UserController {
    private static UserService userService ;

    public static void checkLogin(Context ctx){
        System.out.println("Checking login");
        User user = ctx.sessionAttribute("user");
        if (user != null) {
            System.out.println("Logged in as " + user.getUsername());
            ctx.json(user);
            ctx.status(200);
        } else {
            System.out.println("Not logged in");
            ctx.status(400);
        }
    }

    public static void login(Context ctx){
        System.out.println("Logging In");
        String username = ctx.queryParam("user");
        String password = ctx.queryParam("pass");


        ctx.status(404);
    }

    public static void registerUser(Context ctx){

    }

    public static void logout(Context ctx){

    }
}
