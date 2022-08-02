package com.bilgeadam.boost.recipeapp.server.controller;

import java.util.ArrayList;

import org.hibernate.Session;

import com.bilgeadam.boost.recipeapp.server.dbo.Allergen;

import jakarta.persistence.TypedQuery;

public class AllergenController extends EntityController<Allergen>{

	@Override
	public ArrayList<Allergen> retrieve() {
		Session session = databaseConnectionViaHibernate();
		String hql = "SELECT allergen FROM Allergen as allergen";
		TypedQuery<Allergen> typedQuery = session.createQuery(hql, Allergen.class);
		
		ArrayList<Allergen> allergens = (ArrayList<Allergen>) typedQuery.getResultList();
		return allergens;
	}

}
