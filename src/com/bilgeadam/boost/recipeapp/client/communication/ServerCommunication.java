package com.bilgeadam.boost.recipeapp.client.communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.StringTokenizer;

import com.bilgeadam.boost.recipeapp.client.common.CommonObjects;

public class ServerCommunication {
	private PrintWriter    out;
	private BufferedReader in;

	public ServerCommunication(Socket socket) {
		super();

		try {
			this.out = new PrintWriter(socket.getOutputStream(), true);
			this.in  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void introduceClient(String id) throws IOException {
		out.println("INTR:" + id);
		out.flush();
		String answer = in.readLine();
		System.out.println(">>>" + answer);
	}

	public boolean checkLogin(String email, String password) {
		out.println("LOGIN:" + email + "|" + password);
		out.flush();
		String answer = null;
		try {
			answer = in.readLine();
			if (answer.startsWith("FAILURE")) {
				return false;
			}
			answer = answer.substring(8);
			StringTokenizer tokenizer = new StringTokenizer(answer, "|");
			while (tokenizer.hasMoreTokens()) {
				CommonObjects.getInstance().addRole(tokenizer.nextToken());
			}
			return true;
		}
		catch (IOException ex) {
			System.err.println("Communication failure due <<<" + ex.getMessage() + ">>>");
		}
		return false;
	}

	public void recipeList() {
		out.println("RECIPES");
		String answer = null;
		try {
			answer = in.readLine();
			if (answer.startsWith("FAILURE")) {
				System.out.println("Sistemde herhangi bir yemek tarifi bulunamadı");
			}
			answer = answer.substring(8);
			System.out.println(answer);
		}
		catch (IOException ex) {
			System.err.println("Communication failure due <<<" + ex.getMessage() + ">>>");
		}
	}
}
