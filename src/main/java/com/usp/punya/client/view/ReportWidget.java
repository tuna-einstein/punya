package com.usp.punya.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class ReportWidget extends Composite implements ReportView {

	private static ReportWidgetUiBinder uiBinder = GWT.create(ReportWidgetUiBinder.class);

	interface ReportWidgetUiBinder extends UiBinder<Widget, ReportWidget> {
	}

	public ReportWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

//	@UiField
//	Button button;

	public ReportWidget(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
	//	button.setText(firstName);
	}

//	@UiHandler("button")
//	void onClick(ClickEvent e) {
//		Window.alert("Hello!");
//	}
//
//	public void setText(String text) {
//		button.setText(text);
//	}
//
//	public String getText() {
//		return button.getText();
//	}

}
