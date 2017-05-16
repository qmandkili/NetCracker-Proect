package com.myproject.server;

public interface LoginWorker {
	
	public void addNewUser(String login, String password) throws Exception;
	
	public boolean check(String login, String password);

}
