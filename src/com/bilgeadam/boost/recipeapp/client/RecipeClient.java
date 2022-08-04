package com.bilgeadam.boost.recipeapp.client;

import java.io.IOException;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.UUID;

import com.bilgeadam.boost.recipeapp.RecipeApprovedDatabaseListener;
import com.bilgeadam.boost.recipeapp.RecipeCreatedDatabaseListener;
import com.bilgeadam.boost.recipeapp.client.communication.ServerCommunication;
import com.bilgeadam.boost.recipeapp.client.view.UserInterface;

public class RecipeClient {
	private String              id;
	private ServerCommunication communication;
	private Socket              socket;

	private RecipeClient() {
		super();
		this.id = UUID.randomUUID().toString(); // A universally unique identifier (UUID) is a 128-bit label used for
												// information in computer systems
	}

	public static void main(String[] args) {
		RecipeClient client = new RecipeClient();
		System.out.println("Recipe client with UUID <<<" + client.id + ">>> started");
		client.connect2Server();
		client.connect2Database();
		try {
			client.startUI();
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	private void connect2Database() {
		try {
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost/RecipeDB";
			Connection conn = DriverManager.getConnection(url, "postgres", "root");
			RecipeCreatedDatabaseListener creationListener = new RecipeCreatedDatabaseListener(conn);
			RecipeApprovedDatabaseListener approvalListener = new RecipeApprovedDatabaseListener(conn);
			creationListener.start();
			approvalListener.start();
		}
		catch (ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
		}
	}

	private void startUI() throws IOException {
		UserInterface ui = new UserInterface(communication);
		ui.show();
	}

	private void connect2Server() {
		try {
			this.socket        = new Socket("localhost", 4711);
			this.communication = new ServerCommunication(this.socket);
			communication.introduceClient(this.id);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}