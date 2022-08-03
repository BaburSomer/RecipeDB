package com.bilgeadam.boost.recipeapp.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.bilgeadam.boost.recipeapp.server.dbo.Allergen;
import com.bilgeadam.boost.recipeapp.server.dbo.Ingredient;
import com.bilgeadam.boost.recipeapp.server.dbo.Recipe;
import com.bilgeadam.boost.recipeapp.server.dbo.RecipeLog;
import com.bilgeadam.boost.recipeapp.server.dbo.RecipeType;
import com.bilgeadam.boost.recipeapp.server.dbo.Role;
import com.bilgeadam.boost.recipeapp.server.dbo.Tag;
import com.bilgeadam.boost.recipeapp.server.dbo.Unit;
import com.bilgeadam.boost.recipeapp.server.dbo.User;

public class HibernateUtil {
	private static final SessionFactory sessionFactory = sessionFactoryHibernate(); 
	
	private static SessionFactory sessionFactoryHibernate() {
		SessionFactory factory = null;
		try {
			Configuration config = new Configuration(); // instance oluşturduk
			
			config.addAnnotatedClass(Ingredient.class); 
			config.addAnnotatedClass(Allergen.class); 
			config.addAnnotatedClass(Recipe.class); 
			config.addAnnotatedClass(RecipeLog.class); 
			config.addAnnotatedClass(RecipeType.class); 
			config.addAnnotatedClass(Role.class); 
			config.addAnnotatedClass(Tag.class); 
			config.addAnnotatedClass(Unit.class); 
			config.addAnnotatedClass(User.class); 
			
			factory = config.configure("hibernate.cfg.xml").buildSessionFactory();
		}
		catch (Exception ex) {
			System.err.println("Something went terribly wrong: " + ex.getMessage());
		}
		return factory;
	}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
