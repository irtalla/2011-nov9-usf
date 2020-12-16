package com.revature.controllers;

import com.revature.services.PersonServiceImpl;
import com.revature.services.PersonService;
import com.revature.beans.Person;
import com.revature.exceptions.NonUniqueUsernameException;

import io.javalin.http.Context;

public class PersonController{
   private static PersonService personServ = new PersonServiceImpl();

   public static void checkLogin(Context ctx){
      System.out.println("Checking login...");
      Person p = ctx.sessionAttribute("user");
      if(p != null){
         System.out.println("Logged in as " + p.getUsername());
         ctx.json(p);
         ctx.status(200);
      }
      else{
         System.out.println("Not logged in");
         ctx.status(400);
      }
   }
   public static void logIn(Context ctx){
      System.out.println("Logging in...");
      String username = ctx.queryParam("user");
      String password = ctx.queryParam("pass");

      Person p = personServ.getPersonByUsername(username);
      if(p != null){
         if(p.getPassword().equals(password)){
            System.out.println("Logged in as " + p.getUsername());
            ctx.status(200);
            ctx.json(p);
            ctx.sessionAttribute("user", p);
         }
         else{
            // wrong password
            ctx.status(400);
         }
      }
      else{
         // couldn't find the Username
         ctx.status(404);
      }
   }

   public static void logOut(Context ctx){
      System.out.println("Loging out...");
      ctx.req.getSession().invalidate();
      ctx.status(200);
   }

   public static void registerUSer(Context ctx){
      Person newPerson = ctx.bodyAsClass(Person.class);
      try{
         personServ.addPerson(newPerson);
      }
      catch(NonUniqueUsernameException e){
         System.out.println("Username is taken :-()");
         ctx.status(409);
      }
      ctx.status(200);
   }

   public static void getUserById(Context ctx){
      Integer id = Integer.valueOf(ctx.pathParam("id"));
      Person p = personServ.getPersonById(id);
      if(p != null){
         ctx.status(200);
         ctx.json(p);
      }
      else{
         ctx.status(404);
      }
   }

   public static void updateUser(Context ctx){
      Person tempPerson = ctx.bodyAsClass(Person.class);
      personServ.updatePerson(tempPerson);
      ctx.status(202);
   }

   public static void getRequests(Context ctx){
      Person p = personServ.getPersonById(Integer.valueOf(ctx.pathParam("id")));
      
   }

   public static void deleteUser(Context ctx){
      Integer id = Integer.valueOf(ctx.pathParam("id"));
      Person person = personServ.getPersonById(id);
      personServ.deletePerson(person);
      ctx.status(204);
   }
}
