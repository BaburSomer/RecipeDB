package com.bilgeadam.boost.recipeapp.server.controller;

import java.util.ArrayList;

import org.hibernate.Session;

import com.bilgeadam.boost.recipeapp.server.dbo.User;

import jakarta.persistence.TypedQuery;

public class UserController extends EntityController<User> {

	@Override
	public ArrayList<User> retrieve() {
		Session          session    = databaseConnectionViaHibernate();
		String           hql        = "SELECT user FROM User as user";
		TypedQuery<User> typedQuery = session.createQuery(hql, User.class);

		ArrayList<User> users = (ArrayList<User>) typedQuery.getResultList();
		return users;
	}
}
