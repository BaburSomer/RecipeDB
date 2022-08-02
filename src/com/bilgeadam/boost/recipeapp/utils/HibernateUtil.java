package com.bilgeadam.boost.recipeapp.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.bilgeadam.boost.recipeapp.server.dbo.Allergen;
import com.bilgeadam.boost.recipeapp.server.dbo.Ingredient;

public class HibernateUtil {
	private static final SessionFactory sessionFactory = sessionFactoryHibernate(); 
	
	private static SessionFactory sessionFactoryHibernate() {
		SessionFactory factory = null;
		try {
			Configuration config = new Configuration(); // instance oluşturduk
			
			config.addAnnotatedClass(Ingredient.class); 
			config.addAnnotatedClass(Allergen.class); 
			
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
