package com.bilgeadam.boost.recipeapp.server.controller;

import java.util.ArrayList;

import org.hibernate.Session;

import com.bilgeadam.boost.recipeapp.server.dbo.Ingredient;

import jakarta.persistence.TypedQuery;

public class IngredientController extends EntityController<Ingredient>{

	@Override
	public ArrayList<Ingredient> retrieve() {
		Session session = databaseConnectionViaHibernate();
		String hql = "SELECT ingredient FROM Ingredient as ingredient";
		TypedQuery<Ingredient> typedQuery = session.createQuery(hql, Ingredient.class);
		
		ArrayList<Ingredient> allergens = (ArrayList<Ingredient>) typedQuery.getResultList();
		return allergens;
	}

}
