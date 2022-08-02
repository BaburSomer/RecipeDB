package com.bilgeadam.boost.recipeapp.server.dbo.init;

import java.util.ArrayList;

import com.bilgeadam.boost.recipeapp.server.controller.IngredientController;
import com.bilgeadam.boost.recipeapp.server.dbo.Ingredient;

public class IngredientsTest {

	public static void main(String[] args) {
		IngredientController aC = new IngredientController();
		ArrayList<Ingredient> allergens = aC.retrieve();
		System.out.println(allergens.get(0));
	}
}
