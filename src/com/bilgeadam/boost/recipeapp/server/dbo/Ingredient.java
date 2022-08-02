package com.bilgeadam.boost.recipeapp.server.dbo;

import java.util.Collection;
import java.util.HashSet;

import jakarta.persistence.Column;
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
	
	@ManyToMany()
	@JoinTable(name="ingredients_allergens", joinColumns = {@JoinColumn(name = "ingredients_oid")}, inverseJoinColumns = {@JoinColumn(name = "allergens_oid")})
	private Collection<Allergen> allergens = new HashSet<>();

	public Ingredient(String name) {
		this.name = name;
	}
	
	public void addAllergen(Allergen allergen) {
		this.allergens.add(allergen);
	}
	
	public Collection<Allergen> getAllergens() {
		return this.allergens;
	}
	
}
