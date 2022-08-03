package com.bilgeadam.boost.recipeapp.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.bilgeadam.boost.recipeapp.server.communication.ClientHandler;

public class RecipeServer {

	public static void main(String[] args) {
		(new RecipeServer()).start();
	}

	private void start() {
		System.out.println("Server starting");
		this.startServer(); // client'lar ile ileti�im
		System.out.println("Server stopped");
	}

	private void startServer() {
		try (ServerSocket server = new ServerSocket(4711);) {
			server.setReuseAddress(true); // port'u bir çok client'ın kullanabilece�i �ekilde ayarla.
			System.out.println("Server started and waiting for clients...");
			while (true) {
				Socket client = server.accept();
				System.out.println("New client connected " + client.getInetAddress().getHostAddress());
				ClientHandler clienthandler = new ClientHandler(client); // create a new thread object her yeni ba�lant�
																			// iste�i geldi�inde bir clientHandle
																			// olu�turuyor
				new Thread(clienthandler).start(); // This thread will handle the client separately ve onu yeni bir
													// thread i�inde �al��t�r�yor
			}
		}
		catch (IOException ex) {
			System.err.println(ex.getMessage());
		}
	}
}
