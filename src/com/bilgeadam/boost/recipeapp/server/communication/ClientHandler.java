package com.bilgeadam.boost.recipeapp.server.communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {
	private final Socket   clientSocket;
	private PrintWriter    out;
	private BufferedReader in;

	public ClientHandler(Socket socket) {
		this.clientSocket = socket;
		this.out          = null;
		this.in           = null;
	}

	public void run() {
		try (PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)){;
			 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			this.out = out;
			this.in  = in;

			String line;
			while ((line = this.in.readLine()) != null) {
				this.processRequest(line);
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void processRequest(String line) {
		line = line.trim();
		System.out.println("processing request: " + line);
		this.out.println(line);
		this.out.flush();
	}
}
