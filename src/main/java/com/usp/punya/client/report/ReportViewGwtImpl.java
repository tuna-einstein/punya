package com.usp.punya.client.report;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.polymer.paper.widget.PaperDialog;
import com.vaadin.polymer.paper.widget.PaperDrawerPanel;
import com.vaadin.polymer.paper.widget.PaperInput;
import com.vaadin.polymer.paper.widget.PaperTextarea;

import javax.inject.Inject;


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
	
	public ReportViewGwtImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}



		@Override
		public Widget asWidget() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setPresenter(Presenter presenter) {
			// TODO Auto-generated method stub
			
		}
}
