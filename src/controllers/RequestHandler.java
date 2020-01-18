package controllers;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class RequestHandler implements Runnable {

	private int serverPort = 8080;
	private ServerSocket serverSocket = null;
	private boolean isStopped = false;
	private Thread runningThread = null;
	
	public RequestHandler(int serverPort) {
		this.serverPort = serverPort;
	}
	
	@Override
	public void run() {
		this.openServerSocket();
		while(!this.isStopped()) {
			Socket clientSocket = null;
			try {
				clientSocket = this.serverSocket.accept();
			} catch (IOException e) {
                if (isStopped()) {
                    System.out.println("Server Stopped.");
                    return;
                }
                throw new RuntimeException("Error accepting client connection", e);
            }
			new Thread(new RuleHandler("", clientSocket)).start();
		}
		
	}
	
	private synchronized boolean isStopped() {
		return this.isStopped;
	}
	
	private synchronized void stopServer() {
		this.isStopped = true;
		try {
			this.serverSocket.close();
		} catch (IOException exception) {
			System.out.println(exception.getMessage());
		}
	}
	
	private void openServerSocket() {
		try {
			this.serverSocket = new ServerSocket(this.serverPort);
		} catch(IOException exception) {
			System.out.println(exception.getMessage());
		}
	}
	
}
