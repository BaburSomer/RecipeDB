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
		this.startServer(); // client'lar ile iletişim
		System.out.println("Server stopped");
	}

	private void startServer() {
		try (ServerSocket server = new ServerSocket(4711);) {
			server.setReuseAddress(true); // port'u bir çok client'ın kullanabileceği şekilde ayarla.
			System.out.println("Server started and waiting for clients...");
			while (true) {
				Socket client = server.accept();
				System.out.println("New client connected " + client.getInetAddress().getHostAddress());
				ClientHandler clienthandler = new ClientHandler(client); // create a new thread object her yeni bağlantı
																			// isteği geldiğinde bir clientHandle
																			// oluşturuyor
				new Thread(clienthandler).start(); // This thread will handle the client separately ve onu yeni bir
													// thread içinde çalışıyor
			}
		}
		catch (IOException ex) {
			System.err.println(ex.getMessage());
		}
	}
}
