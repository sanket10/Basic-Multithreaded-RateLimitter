package controllers;

import java.net.Socket;
import models.*;
import stores.*;

import java.io.*;

public class RuleHandler implements Runnable {

	private String serverText = null;
	private Socket clientSocket = null;
	
	public RuleHandler(String serverText, Socket clientSocket) {
		super();
		this.serverText = serverText;
		this.clientSocket = clientSocket;
	}

	@Override
	public void run() {
		try {
			InputStream inputStream = this.clientSocket.getInputStream();
			OutputStream outputStream = this.clientSocket.getOutputStream();
			
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
			String line = bufferedReader.readLine();
			long time = System.currentTimeMillis();
            inputStream.close();
            outputStream.close();
          
            try {
                Request request = new Request(line);
                RulesManager.getIntance().verifyRules(request);
                System.out.println("Request processed: " + time);
            } catch (Exception e) {
                System.out.println("Unable to process request " + e.getMessage());
            }	
		} catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	
}
