package com.bilgeadam.boost.recipeapp.server.dbo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "recipes")
@ToString
@NoArgsConstructor
public class Recipe {
	@Getter
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long   oid;
	@Getter
	@Setter
	private String name;
	@Getter
	@Setter
	private String description;
	@Getter
	@Setter
	private int servings;
	@Getter
	@Setter
	@Column(name = "calories_per_serving")
	private int caloriesPerServing;
	@Getter
	@Setter
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "recipe_type_id", referencedColumnName = "oid")
	private RecipeType recipeType;
	@Getter
	@Setter
	private boolean approved;
	public Recipe(String name, String description, int servings, int caloriesPerServing, RecipeType recipeType,
			boolean approved) {
		super();
		this.name               = name;
		this.description        = description;
		this.servings           = servings;
		this.caloriesPerServing = caloriesPerServing;
		this.recipeType         = recipeType;
		this.approved           = approved;
	}
}
