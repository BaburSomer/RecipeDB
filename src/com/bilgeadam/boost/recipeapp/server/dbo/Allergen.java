package com.bilgeadam.boost.recipeapp.server.dbo;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "allergens")
@NoArgsConstructor
//@ToString (doNotUseGetters = true, exclude = "ingredients") --> many-to-many ilişkilerde problem yaratabilir. karşılıklı 
//																	toString metodunu çağırdığından endless-loop a giriyor. 
//																	ilişkinin olduğu sınıflardan birinde diğer sınıfı exclude etmek gerekli
//						ya da kendi toString metodunuzu yazın
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
	@Getter @Setter
	private Collection<Ingredient> ingredients = new HashSet<>();


	public Allergen(String name) {
		this.name   = name;
		this.symbol = null;
	}
	
	public void addIngredient(Ingredient ingredient) {
		this.ingredients.add(ingredient);
	}

	@Override
	public String toString() {
		String retVal =  "Allergen [oid=" + this.oid + ", name=" + this.name + ", symbol=" + Arrays.toString(this.symbol)
				+ ", ingredients=[\n";
		
		Iterator<Ingredient> iter = this.ingredients.iterator();
		while (iter.hasNext()) {
			Ingredient ingredient = iter.next(); 
			retVal += 	ingredient.getId() + " - " + ingredient.getName() + "\n";
		}
		
		return retVal + "]\n]";
	}
	
	
}
