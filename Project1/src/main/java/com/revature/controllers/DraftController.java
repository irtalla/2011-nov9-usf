package com.revature.controllers;
import com.revature.services.DraftService;
import com.revature.services.DraftServiceImpl;
import com.revature.beans.Draft;
import java.util.Set;
import com.revature.beans.Person;

import io.javalin.http.Context;

public class DraftController {
   private static DraftService draftServ = new DraftServiceImpl();

   public static void getDraftById(Context ctx){
      Integer id = Integer.valueOf(ctx.pathParam("id"));
      Draft draft = draftServ.getDraftById(id);
      if(draft != null){
         ctx.status(200);
         ctx.json(draft);
      }
      else{
         ctx.status(404);
      }
   }

   public static void getAllDrafts(Context ctx){
      Set<Draft> drafts = draftServ.getDrafts();
      if(drafts != null){
         ctx.status(200);
         ctx.json(drafts);
      }
      else{
         ctx.status(404);
      }
   }

   public static void getPendingDrafts(Context ctx){
      System.out.println("Getting pending drafts...");
      Set<Draft> draftSet = draftServ.getPendingDrafts();
      if(draftSet != null){
         ctx.status(200);
         ctx.json(draftSet);
      }
      else{
         ctx.status(404);
      }
   }

   public static void addDraft(Context ctx){
      Draft draft = ctx.bodyAsClass(Draft.class);
      Person currentUser = ctx.sessionAttribute("user");
      draftServ.addDraft(draft, currentUser);
      ctx.status(201);
   }

   public static void updateDraft(Context ctx){
      //Integer id = Integer.valueOf(ctx.pathParam("id"));
      Draft draft = ctx.bodyAsClass(Draft.class);
      if(draft != null){
         ctx.status(200);
         draftServ.updateDraft(draft);
      }
      else{
         ctx.status(404);
      }
   }

   public static void deleteDraft(Context ctx){
      Integer id = Integer.valueOf(ctx.pathParam("id"));
      Draft draft = draftServ.getDraftById(id);
      if(draft != null){
         draftServ.removeDraft(draft);
         ctx.status(204);
      }
      else{
         ctx.status(204);
      }
   }

   public static void approveDraft(Context ctx){
      Person currentUser = ctx.sessionAttribute("user");
      Draft draft = draftServ.getDraftById(Integer.valueOf(ctx.pathParam("id")));
      if(currentUser != null){
         if(draft != null){
            draftServ.approveDraft(draft, currentUser);
            ctx.status(200);
         }
         else{
            ctx.status(409);
         }
      }
      else{
         ctx.status(401);
      }
   }

   public static void rejectDraft(Context ctx){
      Person currentUser = ctx.sessionAttribute("user");
      Draft draft = draftServ.getDraftById(Integer.valueOf(ctx.pathParam("id")));
      if(currentUser != null){
         if(draft != null){
            draftServ.rejectDraft(draft);
            ctx.status(200);
         }
         else{
            ctx.status(409);
         }
      }
      else{
         ctx.status(401);
      }
   }
}
