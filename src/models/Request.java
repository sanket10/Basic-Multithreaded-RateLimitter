package models;
import java.util.*;

public class Request {
	private String request;
	private List<String> requestParameter;
	
	public Request(String request) {
		this.request = request;
		this.requestParameter = new ArrayList<String>();
		this.updateRequestParameter();
	}
	
	private void updateRequestParameter() {
		for (String parameter: this.request.split("\\s+")) {
			this.requestParameter.add(parameter);
		}
	}

	public List<String> getRequestParameter() {
		return this.requestParameter;
	}
	
}
