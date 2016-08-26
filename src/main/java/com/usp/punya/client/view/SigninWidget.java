package com.usp.punya.client.view;

import javax.inject.Singleton;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.TouchEndEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.ui.client.widget.button.Button;

public class SigninWidget extends Composite implements SigninView {

	private static SigninWidgetUiBinder uiBinder = GWT.create(SigninWidgetUiBinder.class);

	interface SigninWidgetUiBinder extends UiBinder<Widget, SigninWidget> {
	}

	private Bridge bridge;
	
	@UiField
	Button signinButton;
	
	public SigninWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	@UiHandler("signinButton")
	void onTouchEnd(TouchEndEvent arg0) {
		if (bridge != null) {
			bridge.onSigninClick();
		}
	}

	@Override
	public void setBridge(Bridge bridge) {
		this.bridge = bridge;
	}
}
