package com.usp.punya.client;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.History;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.HandlerRegistration;
import com.googlecode.mgwt.dom.client.event.mouse.HandlerRegistrationCollection;
import com.googlecode.mgwt.mvp.client.history.HistoryHandler;
import com.googlecode.mgwt.mvp.client.history.HistoryObserver;
import com.usp.punya.client.event.ActionEvent;
import com.usp.punya.client.event.ActionNames;

public class AppHistoryObserver implements HistoryObserver {

	@Override
	public void onPlaceChange(Place place, HistoryHandler handler) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onHistoryChanged(Place place, HistoryHandler handler) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAppStarted(Place place, HistoryHandler historyHandler) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public HandlerRegistration bind(EventBus eventBus, HistoryHandler historyHandler) {
		HandlerRegistration backButtonRegistration = ActionEvent.register(
				eventBus, ActionNames.BACK, new ActionEvent.Handler() {

					@Override
					public void onAction(ActionEvent event) {
						History.back();
					}
				});

		HandlerRegistration animationEndRegistration = ActionEvent.register(
				eventBus, ActionNames.ANIMATION_END, new ActionEvent.Handler() {

					@Override
					public void onAction(ActionEvent event) {
						History.back();
					}
				});

		HandlerRegistrationCollection col = new HandlerRegistrationCollection();
		col.addHandlerRegistration(backButtonRegistration);
		col.addHandlerRegistration(animationEndRegistration);

		return col;
	}
	
}
