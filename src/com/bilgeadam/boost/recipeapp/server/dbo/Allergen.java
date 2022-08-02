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

@Entity
@Table(name = "allergens")
@NoArgsConstructor
public class Allergen {
	@Getter
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long   oid;
	@Getter
	@Setter
	private String name;
	@Getter
	@Setter
	private byte[] symbol;
	
	@ManyToMany(mappedBy = "allergens")
	private Collection<Ingredient> ingredients = new HashSet<>();


	public Allergen(String name) {
		this.name   = name;
		this.symbol = null;
	}
	
	public Collection<Ingredient> getIngredients() {
		return this.ingredients;
	}

}
