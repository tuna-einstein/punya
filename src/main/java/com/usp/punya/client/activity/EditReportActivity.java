package com.usp.punya.client.activity;

import javax.inject.Inject;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.usp.punya.client.view.ReportView;

public class EditReportActivity extends AbstractActivity {

	@Inject
	ReportView reportView;
	
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(reportView);
	}

}
