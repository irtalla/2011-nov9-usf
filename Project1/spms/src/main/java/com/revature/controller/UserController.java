package com.revature.controller;

import com.revature.beans.User;
import com.revature.service.UserService;
import com.revature.service.UserServiceImpl;
import io.javalin.http.Context;

public class UserController {
    private static UserServiceImpl userService = new UserServiceImpl();

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

        Integer result = userService.authenticate(username, password);
        System.out.println("Authenticate Result: " + result);
        switch (result){
            case (1):   ctx.status(404);
                        break;
            case (2):   ctx.status(400);
                        break;
            case (3):   User user = userService.getUserByUsername(username);
                        ctx.status(200);
                        ctx.json(user);
                        ctx.sessionAttribute("user", user);
                        break;
            default:    ctx.status(404);
                        break;
        }
    }

    public static void registerUser(Context ctx){
        
    }

    public static void logout(Context ctx){
        System.out.println("Logging out");
        ctx.req.getSession().invalidate();
        ctx.status(200);
    }
}
