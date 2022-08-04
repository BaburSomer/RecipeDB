package com.bilgeadam.boost.recipeapp.server.communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import com.bilgeadam.boost.recipeapp.server.common.CommonObjects;
import com.bilgeadam.boost.recipeapp.server.dbo.Recipe;
import com.bilgeadam.boost.recipeapp.server.dbo.Role;
import com.bilgeadam.boost.recipeapp.server.dbo.User;

public class ClientHandler implements Runnable {
	private final Socket   clientSocket;
	private PrintWriter    out;
	private BufferedReader in;

	public ClientHandler(Socket socket) {
		this.clientSocket = socket;
		this.out          = null;
		this.in           = null;
	}

	public void run() {
		try (PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
			;
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			this.out = out;
			this.in  = in;

			String line;
			while ((line = this.in.readLine()) != null) {
				this.processRequest(line);
			}
		}
		catch (IOException e) {
			System.out.println("Communication with client ended");
		}
	}

	private void processRequest(String line) {
		line = line.trim();
		System.out.println("processing request: " + line);

		String answer = null;
		if (line.toUpperCase().startsWith("INTR:")) {
			answer = this.registerClient(line.substring(5));
		}
		else if (line.toUpperCase().startsWith("LOGIN:")) {
			answer = this.checkLogin(line.substring(6));
		}
		else if (line.toUpperCase().startsWith("RECIPES")) {
			answer = this.listRecipes();
		}

		this.out.println(answer);
		this.out.flush();
	}

	private String listRecipes() {
		List<Recipe> recipes = CommonObjects.getInstance().getRecipes();
		if (recipes.isEmpty()) {
			return "FAILURE|No recipes found";
		}
		StringBuffer answer = new StringBuffer("SUCCESS");
		for (Recipe recipe : recipes) {
			if (recipe.isApproved()) {
				answer.append("|");
				answer.append(recipe.getName()).append("~");
				answer.append(recipe.getDescription()).append("~");
				answer.append(recipe.getServings()).append("~");
				answer.append(recipe.getCaloriesPerServing()).append("~");
				answer.append(recipe.getCaloriesPerServing()).append("~");
				answer.append(recipe.getRecipeType().getName());
			}
		}
		return answer.toString();
	}

	private String checkLogin(String message) {
		StringTokenizer tokenizer = new StringTokenizer(message, "|");
		User            user      = CommonObjects.getInstance().checkUserCredentials(tokenizer.nextToken(),
				tokenizer.nextToken());
		if (user == null) {
			return "FAILURE|Wrong Login";
		}

		String         answer = "SUCCESS";
		Iterator<Role> iter   = user.getRoles().iterator();
		while (iter.hasNext()) {
			String roleName = iter.next().getName();
			answer += "|" + roleName;
		}
		return answer;
	}

	private String registerClient(String message) {
		System.out.println("Mew client with UUID <<<" + message + ">>> registered");
		return "ACCEPTED";
	}
}
