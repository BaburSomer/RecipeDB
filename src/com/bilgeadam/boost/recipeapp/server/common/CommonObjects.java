package com.bilgeadam.boost.recipeapp.server.common;

import java.util.ArrayList;
import java.util.HashMap;

import com.bilgeadam.boost.recipeapp.server.controller.UserController;
import com.bilgeadam.boost.recipeapp.server.dbo.User;

public class CommonObjects {
	private static CommonObjects  instance;
	private HashMap<String, User> users;

	private CommonObjects() {
		super();
	}

	public static CommonObjects getInstance() {
		if (CommonObjects.instance == null) {
			CommonObjects.instance = new CommonObjects();
		}
		return CommonObjects.instance;
	}

	public User checkUserCredentials(String email, String password) {
		User user = this.getUsers().get(email);
		if (user != null) {
			boolean correctPassword = user.getPassword().equals(password);
			if (!correctPassword) {
				user = null;
			}
		}
		return user;
	}

	private HashMap<String, User> getUsers() {
		if (this.users == null) {
			this.loadUsers();
		}
		return this.users;
	}

	private void loadUsers() {
		this.users = new HashMap<>();
		UserController  controller = new UserController();
		ArrayList<User> users      = controller.retrieve();
		for (User user : users) {
			this.users.put(user.getEmail(), user);
		}
	}
}
