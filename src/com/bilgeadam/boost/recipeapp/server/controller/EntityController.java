package com.bilgeadam.boost.recipeapp.server.controller;

import java.util.ArrayList;

import org.hibernate.Session;

import com.bilgeadam.boost.recipeapp.server.dbo.Recipe;
import com.bilgeadam.boost.recipeapp.utils.HibernateUtil;

import jakarta.persistence.TypedQuery;

public class EntityController<T> implements CRUDable<T>{

	@Override
	public void create(T entity) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.getTransaction().begin();
		session.persist(entity);
		session.getTransaction().commit();
	}

	@Override
	public ArrayList<T> retrieve() {
//		Session          session    = databaseConnectionViaHibernate();
//		String           hql        = "SELECT recipe FROM Recipe as recipe";
//		TypedQuery<T> typedQuery = session.createQuery(hql, T);
//
//		ArrayList<T> entities = (ArrayList<T>) typedQuery.getResultList();
//		return entities;
		return null;
	}

	@Override
	public void update(T oldOne, T newOne) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(T entity) {
		// TODO Auto-generated method stub
		
	}

}
