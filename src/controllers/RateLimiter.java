package controllers;
import models.*;

public class RateLimiter {
	
	public static void main(String args[]) {
		RequestHandler server = new RequestHandler(9000);
		Rule rule1 = new Rule(RuleType.API_ENDPOINT, 3, 1, "path", "/users");
		Rule rule2 = new Rule(RuleType.API_ENDPOINT, 2, 1, "apiType", "get");
		
		new Thread(server).start();
	}
}
