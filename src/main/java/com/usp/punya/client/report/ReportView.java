package com.usp.punya.client.report;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.inject.ImplementedBy;

public interface ReportView extends IsWidget {
	
	public void setPresenter(Presenter presenter);
	
	public interface Presenter {
		
		public void onBackButtonPressed();
		
	}
}