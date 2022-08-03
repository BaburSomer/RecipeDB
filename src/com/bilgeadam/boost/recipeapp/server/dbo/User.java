package com.bilgeadam.boost.recipeapp.server.dbo;

import java.util.Collection;
import java.util.HashSet;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "users")
@ToString
@NoArgsConstructor
public class User {
	@Getter
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long   oid;
	@Getter
	@Setter
	private String firstName;
	private String surname;
	private String email;
	private String password;
	private double rating;

	@ManyToMany(mappedBy = "roles")
	@Getter @Setter
	private Collection<Role> roles = new HashSet<>();

	public User(String firstName, String surname, String email, String password, double rating) {
		super();
		this.firstName = firstName;
		this.surname   = surname;
		this.email     = email;
		this.password  = password;
		this.rating    = rating;
	}
	
	public void addRole(Role role) {
		this.roles.add(role);
	}
}
