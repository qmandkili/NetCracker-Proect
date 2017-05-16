package com.myproject.client;


import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.myproject.server.LoginChecker;



/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class NetCracker_Project implements EntryPoint {
	
	//private final LoginServiceAsync loginService = GWT.create(LoginService.class);

	@Override
	public void onModuleLoad() {
		final HorizontalPanel mainPanel = new HorizontalPanel();
		final Button loginButton = new Button("Login");
		final Button signUpButton = new Button("Sign up");
		mainPanel.add(loginButton);
		mainPanel.add(signUpButton);
		
		RootPanel.get("mainContainer").add(mainPanel);
		
		final DialogBox dialogSignUp = new DialogBox();
		dialogSignUp.setText("Sign up form");
		dialogSignUp.setAnimationEnabled(true);
		final Button sendButton = new Button("Send");
		final TextBox loginTextBox = new TextBox();
		final PasswordTextBox passwordTextBox = new PasswordTextBox();
		VerticalPanel verticalDialogPanel = new VerticalPanel();
		verticalDialogPanel.add(new HTML("<b>Create new login:</b>"));
		verticalDialogPanel.add(loginTextBox);
		verticalDialogPanel.add(new HTML("<b>Create password:</b>"));
		verticalDialogPanel.add(passwordTextBox);
		verticalDialogPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		verticalDialogPanel.add(sendButton);
		dialogSignUp.setWidget(verticalDialogPanel);
		
		signUpButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialogSignUp.setVisible(true);
				dialogSignUp.center();
				signUpButton.setEnabled(false);
				//dialogSignUp.setAnimationEnabled(true);
				//dialogBox.hide();
				//loginButton.setEnabled(true);
				//loginButton.setFocus(true);
			}
		});
		
		sendButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				
				
				dialogSignUp.hide();
				signUpButton.setEnabled(true);
			}
		});
	}
	
	
	
}
