package com.myproject.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.myproject.client.LoginService;
import com.myproject.shared.FieldVerifier;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class LoginServiceImpl extends RemoteServiceServlet implements LoginService {
	
	private String filePath = "LoginData.txt";

	public String addNewUser(String login) throws Exception {
		// System.out.println(login + password);
		String[] splitted = login.split("---");
		login = splitted[0];
		String password = splitted[1];
		File file = new File(filePath);
		if (!login.equals(null) && !password.equals(null)) {
			FileWriter fileWriter;
			try {
				fileWriter = new FileWriter(file, true);
				fileWriter.write(login + "---" + password + "\n");
				fileWriter.close();
			} catch (IOException e) {
				//
			}
		} else {
			throw new Exception();
		}
		String result = "result";
		return result;
	}

	public boolean check(String login, String password) {
		boolean result = false;
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8));
			String line;
			while ((line = reader.readLine()) != null) {
				String[] splittedLine = line.split("---");
				if (splittedLine[0].equals(login) && splittedLine[1].equals(password)) {
					result = true;
					break;
				} else {
					result = false;
				}
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}
	
	public String greetServer(String input) throws IllegalArgumentException {
		// Verify that the input is valid. 
		if (!FieldVerifier.isValidName(input)) {
			// If the input is not valid, throw an IllegalArgumentException back to
			// the client.
			throw new IllegalArgumentException("Name must be at least 4 characters long");
		}

		String serverInfo = getServletContext().getServerInfo();
		String userAgent = getThreadLocalRequest().getHeader("User-Agent");

		// Escape data from the client to avoid cross-site script vulnerabilities.
		input = escapeHtml(input);
		userAgent = escapeHtml(userAgent);

		return "Hello, " + input + "!<br><br>I am running " + serverInfo + ".<br><br>It looks like you are using:<br>"
				+ userAgent;
	}

	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html the html string to escape
	 * @return the escaped string
	 */
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;");
	}
}
