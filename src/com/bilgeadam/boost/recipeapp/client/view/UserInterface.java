package com.bilgeadam.boost.recipeapp.client.view;

import java.util.HashMap;

import com.bilgeadam.boost.recipeapp.client.common.CommonObjects;
import com.bilgeadam.boost.recipeapp.client.communication.ServerCommunication;
import com.bilgeadam.util.BAUtil;

public class UserInterface {

	private ServerCommunication communication;
	HashMap<Integer, String>    menuItems;

	public UserInterface(ServerCommunication communication) {
		this.communication = communication;
	}

	public void show() {
		BAUtil.header("Lezziz Yemek Tarifleri");

		boolean loginOK = false;
		do {
			String email    = BAUtil.readString("Lütfen Kullanıcı e-mail'inizi giriniz: ");
			String password = BAUtil.readString("Lütfen Kullanıcı Şifrenizi giriniz: ");
			loginOK = this.communication.checkLogin(email, password);
			if (!loginOK) {
				System.err.println("Kullanıcı email'i veya şifresi yanlış!");
			}
		} while (!loginOK);

		int selection = -1;
		boolean  exitMenu = false;
		while (!exitMenu) {
			selection = BAUtil.menu(this.getMenuItems());
			if (selection == 99) {
				exitMenu = BAUtil.wantToEnd("Gerçekten mi (E/H)?", "H");
			}
			else {
				this.processSelection(selection);
			}
		}
		BAUtil.footer();
	}

	private void processSelection(int selection) {
		switch (selection) {
			case 1:
				this.communication.recipeList();
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + selection);
		}
	}

	private HashMap<Integer, String> getMenuItems() {
		if (this.menuItems == null) {
			this.initMenu();
		}
		return this.menuItems;
	}

	private void initMenu() {
		this.menuItems = new HashMap<>();
		this.menuItems.put(1, "Tarif Listesi");
		if (CommonObjects.getInstance().hasSuperAdminRole()) {
			this.menuItems.put(2, "Yeni Kullanıcı Tanımla");
		}
		if (CommonObjects.getInstance().hasAdminRole()) {
			this.menuItems.put(3, "Tarif Onayla");
		}
		this.menuItems.put(4, "Yeni Tarif Gir");
		this.menuItems.put(99, "Programı sonlandır");
	}
}
