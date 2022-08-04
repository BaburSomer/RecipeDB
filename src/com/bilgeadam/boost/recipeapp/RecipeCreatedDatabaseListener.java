package com.bilgeadam.boost.recipeapp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.postgresql.PGConnection;
import org.postgresql.PGNotification;

import com.bilgeadam.boost.recipeapp.client.common.CommonObjects;

public class RecipeCreatedDatabaseListener extends Thread {
	private Connection   conn;
	private PGConnection pgConn;
	
	public RecipeCreatedDatabaseListener(Connection conn) throws SQLException {
		super();
		this.conn = conn;
		this.pgConn = (PGConnection)conn;
		Statement stmt = conn.createStatement();
		stmt.execute("LISTEN new_recipe_created");
		stmt.close();
	}
	
	public void run() {
		try {
			while (true) {
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT 1"); 
				rs.close();
				stmt.close();
				
				PGNotification[] notifications = pgConn.getNotifications();
				if (notifications != null) {
					for (PGNotification notification : notifications) {
						if (CommonObjects.getInstance().hasAdminRole() || CommonObjects.getInstance().hasSuperAdminRole()) {
							System.out.println("Yeni bir tarif yaratıldı " + notification.getParameter());
						}
					}
				}
				else {
					System.out.println("No news good news");
				}
			}
		}
		catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
}
