package com.revature.app;

import com.revature.controller.EditorController;
import com.revature.controller.UserController;
import io.javalin.Javalin;

import static io.javalin.apibuilder.ApiBuilder.*;

public class OnlinePublisherJavalin {

    public static void main(String[] args) {
        Javalin app = Javalin.create((config) -> {
           config.addStaticFiles("/static");
           config.enableCorsForAllOrigins();
        });

        app.start(8080);

        app.routes(() -> {

            path("users", () -> {
                get(UserController::checkLogin);
                put(UserController::login);
                post(UserController::registerUser);
                delete(UserController::logout);
                path(":id", () -> {
                    get(UserController::getAuthorEditor);
                });
            });
            path("author",() ->{

            });
            path("editor",() ->{
                path(":id", ()-> {
                    get(EditorController::generateApprovalList);
                });
            });

        });







    }
}
