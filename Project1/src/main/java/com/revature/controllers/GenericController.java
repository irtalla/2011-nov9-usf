package com.revature.controllers;

import java.util.Set;

import com.revature.services.GenericServiceImpl;

import io.javalin.http.Context;

public abstract class GenericController<T> {
	abstract GenericServiceImpl<T> getServ();
	protected Class<T> type;
	
	public GenericController(Class<T> type) {
		this.type = type;
	}
	
	public void getById(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		T t = getServ().getByIdEagerly(id);
		if (t != null) {
			ctx.status(200);
			ctx.json(t);
		} else {
			ctx.status(404);
		}
	}
	
	public  void getAll(Context ctx) {
		Set<T> tSet = getServ().getAllLazily();
		if (tSet != null) {
			ctx.status(200);
			ctx.json(tSet);
		} else {
			ctx.status(404);
		}
	}
	
	public void add(Context ctx) {
		T t = ctx.bodyAsClass(this.type);
		Integer id = null;
		if(t != null) {
			id = getServ().add(t);
			ctx.status(200);
			ctx.json(getServ().getByIdEagerly(id));
		}else {
			ctx.status(400);
		}
	}
	
	public  void update(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		T t = ctx.bodyAsClass(this.type);
		if (t != null) {
			ctx.status(200);
			getServ().update(t);
		} else {
			ctx.status(404);
		}
	}
	
	public void delete(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		T t = getServ().getByIdLazily(id);
		if (t != null) {
			getServ().delete(t);
			ctx.status(204); // 204 = no content
		}
		else {
			ctx.status(404);
		}
	}
}
