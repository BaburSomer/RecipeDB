package com.bilgeadam.boost.recipeapp.server.dbo;

import java.util.Collection;
import java.util.HashSet;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "roles")
@ToString
@NoArgsConstructor
public class Role {
	@Getter
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long   oid;
	@Getter
	@Setter
	private String name;
	@ManyToMany()
	@JoinTable(name="users_roles", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "role_id")})
	private Collection<User> users = new HashSet<>();
	
	public Role(String name) {
		super();
		this.name = name;
	}
	
	public void addUser(User user) {
		this.users.add(user);
	}
	
	public Collection<User> getUsers() {
		return this.users;
	}
	
	public void setUsers(Collection<User> users) {
		this.users = users;
	}

}
