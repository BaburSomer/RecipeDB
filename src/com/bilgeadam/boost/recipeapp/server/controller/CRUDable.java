package com.bilgeadam.boost.recipeapp.server.controller;

import java.util.ArrayList;

import org.hibernate.Session;

import com.bilgeadam.boost.recipeapp.utils.HibernateUtil;


/**
 * C ==> CREATE (insert) R ==> RETRIEVE U ==> UPDATE D ==> DELETE
 *
 */
public interface CRUDable<T> {
	
	void create(T entity);

	ArrayList<T> retrieve();
	
	void update(T oldOne, T newOne);

	void delete(T entity);
	
	default Session databaseConnectionViaHibernate() {
		return HibernateUtil.getSessionFactory().openSession();
	}
}