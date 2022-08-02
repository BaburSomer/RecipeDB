package com.bilgeadam.boost.recipeapp.server.dbo.init;

import com.bilgeadam.boost.recipeapp.server.controller.EntityController;
import com.bilgeadam.boost.recipeapp.server.dbo.Ingredient;

public class InitIngredients {

	public static void main(String[] args) {
		EntityController<Ingredient> controller = new EntityController<Ingredient>();

		Ingredient i1 = new Ingredient("Pirinç");
		Ingredient i2 = new Ingredient("Tuz");
		Ingredient i3 = new Ingredient("Biber");
		Ingredient i4 = new Ingredient("Sıvı Yağ");
		Ingredient i5 = new Ingredient("Kıyma");
		
		controller.create(i1);
		controller.create(i2);
		controller.create(i3);
		controller.create(i4);
		controller.create(i5);
	}
}
