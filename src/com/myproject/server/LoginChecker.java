package com.myproject.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;


public class LoginChecker extends RemoteServiceServlet implements LoginWorker {

	private String filePath = "LoginData.txt";

	public void addNewUser(String login, String password) throws Exception {
		// System.out.println(login + password);
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

	public static void main(String[] args) throws Exception {
		/*LoginChecker loginChecker = new LoginChecker();
		loginChecker.addNewUser("asda", "asdasd");
		loginChecker.addNewUser("dd", "asasddasd");
		loginChecker.addNewUser("assadda", "adadsdasd");
		loginChecker.addNewUser("Anton", "1234");
		loginChecker.addNewUser("asd21a", "asdasdfvv");
		System.out.println(loginChecker.check("Anton", "1234"));*/
	}

}
