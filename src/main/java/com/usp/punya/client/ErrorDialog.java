package com.usp.punya.client;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.google.gwt.user.client.Window;

@Singleton
public class ErrorDialog {
	
	@Inject
	ErrorDialog() {}
	
	public void showError(Throwable t) {
		 // got no callback - well, goodbye
        if (Window.confirm(t.toString() 
        		+ "....something severly went wrong - error - reload page ?")) {
            // Super severe error.
            // reload app or redirect.
            // ===> this breaks the app but that's by intention.
            Window.Location.reload();
        }
	}

}
