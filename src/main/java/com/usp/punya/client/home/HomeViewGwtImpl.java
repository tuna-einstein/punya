package com.usp.punya.client.home;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class HomeViewGwtImpl extends Composite implements HomeView {

	/**
	 * The UiBinder interface.
	 */	
	interface HomeViewGwtImplUiBinder extends UiBinder<Widget, HomeViewGwtImpl> {
	}
	
	/**
	 * The UiBinder used to generate the view.
	 */
	private static HomeViewGwtImplUiBinder uiBinder = GWT
			.create(HomeViewGwtImplUiBinder.class);
	
	public HomeViewGwtImpl() {
        initWidget(uiBinder.createAndBindUi(this));
      }

	@Override
	public void setPresenter(Presenter presenter) {
		// TODO Auto-generated method stub
		
	}
	
	

}