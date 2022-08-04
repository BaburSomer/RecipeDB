package com.bilgeadam.boost.recipeapp.client.common;

import java.util.ArrayList;
import java.util.List;

import com.bilgeadam.boost.recipeapp.common.Role;

public class CommonObjects {
	private static CommonObjects instance;
	private List<Role>           roles;

	private CommonObjects() {
		super();
	}

	public static CommonObjects getInstance() {
		if (CommonObjects.instance == null) {
			CommonObjects.instance = new CommonObjects();
		}
		return CommonObjects.instance;
	}

	public List<Role> getRoles() {
		if (this.roles == null) {
			this.roles = new ArrayList<>();
		}
		return this.roles;
	}

	public void addRole(String roleName) {
		this.getRoles().add(Role.getRole(roleName));
	}

	public boolean hasRole(Role role) {
		return this.getRoles().contains(role);
	}

	public boolean hasSuperAdminRole() {
		return this.getRoles().contains(Role.SUPERUSER);
	}

	public boolean hasAdminRole() {
		return this.getRoles().contains(Role.ADMIN);
	}

	public boolean hasUserRole() {
		return this.getRoles().contains(Role.USER);
	}

	public boolean hasCordonBlueRole() {
		return this.getRoles().contains(Role.CORDONBLUE);
	}
}
