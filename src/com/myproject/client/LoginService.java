package com.myproject.client;


import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("login")
public interface LoginService extends RemoteService {
	String greetServer(String name) throws IllegalArgumentException;
	
	String addNewUser(String login) throws Exception;
}
