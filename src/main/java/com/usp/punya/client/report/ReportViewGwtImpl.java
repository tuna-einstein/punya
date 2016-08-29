package com.usp.punya.client.report;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.ui.client.widget.button.image.PreviousitemImageButton;

public class ReportViewGwtImpl extends Composite implements ReportView {

	/**
	 * The UiBinder interface.
	 */	
	interface ReportViewGwtImplUiBinder extends
	UiBinder<Widget, ReportViewGwtImpl> {
	}

	/**
	 * The UiBinder used to generate the view.
	 */
	private static ReportViewGwtImplUiBinder uiBinder = GWT
			.create(ReportViewGwtImplUiBinder.class);


	@UiField
	PreviousitemImageButton backButton;

	private Presenter presenter;

	public ReportViewGwtImpl() {

		initWidget(uiBinder.createAndBindUi(this));

	}

	@UiHandler("backButton")
	protected void onBackButtonPressed(TapEvent event) {
		if (presenter != null) {
			presenter.onBackButtonPressed();
		}
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}
}
