package com.usp.punya.client.report;

import javax.inject.Inject;

import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.event.shared.EventBus;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;
import com.usp.punya.client.event.ActionEvent;
import com.usp.punya.client.event.ActionNames;

public class ReportActivity extends MGWTAbstractActivity implements ReportView.Presenter {


	@Inject
	ReportView view;
	@Inject EventBus eventBus;

	@Override
	public void start(AcceptsOneWidget panel, final EventBus eventBus) {
		view.setPresenter(this);
		panel.setWidget(view);
	}

	@Override
	public void onStop() {
		view.setPresenter(null);
	}

	@Override
	public void onBackButtonPressed() {
		ActionEvent.fire(eventBus, ActionNames.BACK);
	}

}
