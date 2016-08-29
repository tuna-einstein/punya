package com.usp.punya.client.signin;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.user.client.ui.IsWidget;

public interface SigninView extends IsWidget {
	
	public interface Bridge extends EventHandler {
		void onSigninClick();
	}
	
	void setBridge(Bridge bridge);

}
