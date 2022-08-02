package com.bilgeadam.boost.recipeapp.server.dbo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ingredients")
@NoArgsConstructor
public class Ingredient {
	@Getter
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "oid")
	private long   id;
	@Getter
	@Setter
	private String name;

	public Ingredient(String name) {
		this.name = name;
	}
}
