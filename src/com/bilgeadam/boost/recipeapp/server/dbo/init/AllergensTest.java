package com.bilgeadam.boost.recipeapp.server.dbo.init;

import java.util.ArrayList;

import com.bilgeadam.boost.recipeapp.server.controller.AllergenController;
import com.bilgeadam.boost.recipeapp.server.dbo.Allergen;

public class AllergensTest {

	public static void main(String[] args) {
		AllergenController aC = new AllergenController();
		ArrayList<Allergen> allergens = aC.retrieve();
		System.out.println(allergens);
	}
}
