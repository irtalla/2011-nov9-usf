package com.revature.controller;

import io.javalin.Javalin;

public class TestJavalin {

    public static void main(String[] args) {
        Javalin app = Javalin.create().start(7000);
        app.get("/", ctx -> ctx.result("Javalin Test"));
        app.get("/entry", ctx -> ctx.result("Javalin Entry"));
    }
}
