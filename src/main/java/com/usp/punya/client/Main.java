package com.usp.punya.client;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.RootPanel;
import com.googlecode.mgwt.mvp.client.AnimatingActivityManager;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.MGWTSettings;
import com.googlecode.mgwt.ui.client.widget.animation.AnimationWidget;
import com.usp.punya.client.place.SigninPlace;
import com.usp.punya.client.proxy.BookService;
import com.usp.punya.shared.model.Book;

public class Main implements EntryPoint {
			
	@Inject EventBus eventBus;
	@Inject Logger logger;
	@Inject AnimatingActivityManager animatingActivityManager;
	@Inject PlaceController placeController;
	@Inject AnimatingActivityManager activityManager;
	
	public void onModuleLoad() {
		
		Injector.INSTANCE.injectEntryPoint(this);
		
		// set viewport and other settings for mobile
		MGWT.applySettings(MGWTSettings.getAppSetting());
		
		AnimationWidget appWidget = new AnimationWidget(); 
		RootPanel.get().add(appWidget);
		activityManager.setDisplay(appWidget);
		placeController.goTo(new SigninPlace());
	}
	
	private void readBooks() {
		 BookService bookService = GWT.create(BookService.class);
		 bookService.listBooks(new MethodCallback<List<Book>>() {
			
			@Override
			public void onSuccess(Method method, List<Book> response) {
				logger.log(Level.WARNING, "Got it");
				GWT.log("Got it");
			}
			
			@Override
			public void onFailure(Method method, Throwable exception) {
				logger.log(Level.WARNING, "Error", exception);
				
			}
		});
	}
}
