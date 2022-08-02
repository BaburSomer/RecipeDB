package com.bilgeadam.boost.recipeapp.server.dbo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

	public Allergen(String name) {
		this.name   = name;
		this.symbol = null;
	}
}
