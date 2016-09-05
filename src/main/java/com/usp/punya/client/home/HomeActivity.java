package com.usp.punya.client.home;

import javax.inject.Inject;
import javax.inject.Provider;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.event.shared.EventBus;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;
import com.googlecode.mgwt.ui.client.widget.menu.swipe.SwipeMenu;

public class HomeActivity extends MGWTAbstractActivity implements HomeView.Presenter {

	@Inject HomeView view;
	@Inject Provider<SwipeMenu> swipeMenu;
	@Inject PlaceController placeController;

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		view.setPresenter(this);
		GWT.log("Start HomeActivity");
		panel.setWidget(view);
	}

 
    @Override
    public void onItemSelected(int index) {
           
    }

    @Override
    public void onMenuButtonPressed() {
    	
   
    }
}