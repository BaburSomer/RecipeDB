package com.bilgeadam.boost.recipeapp.server.controller;

import java.util.ArrayList;

import org.hibernate.Session;

import com.bilgeadam.boost.recipeapp.server.dbo.Recipe;

import jakarta.persistence.TypedQuery;

public class RecipeController extends EntityController<Recipe> {

	@Override
	public ArrayList<Recipe> retrieve() {
		Session          session    = databaseConnectionViaHibernate();
		String           hql        = "SELECT recipe FROM Recipe as recipe";
		TypedQuery<Recipe> typedQuery = session.createQuery(hql, Recipe.class);

		ArrayList<Recipe> entities = (ArrayList<Recipe>) typedQuery.getResultList();
		return entities;
	}
}
