package com.usp.punya.client;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.web.bindery.event.shared.EventBus;
import com.googlecode.mgwt.mvp.client.AnimatingActivityManager;
import com.googlecode.mgwt.mvp.client.history.MGWTPlaceHistoryHandler;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.MGWTSettings;
import com.googlecode.mgwt.ui.client.widget.menu.swipe.SwipeMenu;
import com.usp.punya.client.home.HomePlace;
import com.usp.punya.client.navmenu.NavMenu;
import com.usp.punya.client.proxy.BookService;
import com.usp.punya.shared.model.Book;

public class Main implements EntryPoint {
			
	@Inject Logger logger;
	@Inject AnimatingActivityManager animatingActivityManager;
	@Inject PlaceController placeController;
	@Inject AnimatingActivityManager activityManager;
	@Inject @NavMenu AnimatingActivityManager navMenuActivityManager;
	@Inject SwipeMenu swipeMenu;
	@Inject MGWTPlaceHistoryHandler mGWTPlaceHistoryHandler;
	@Inject EventBus eventBus;
	
	public void onModuleLoad() {
		
		Injector.INSTANCE.injectEntryPoint(this);
		
		// set viewport and other settings for mobile
		MGWT.applySettings(MGWTSettings.getAppSetting());
		
//		AnimationWidget appWidget = new AnimationWidget();
//		activityManager.setDisplay(appWidget);
//		SwipeMenu swipeMenu1 = new SwipeMenu();
//		swipeMenu1.setContentDisplay(appWidget);
//		
//		AnimationWidget navDisplay = new AnimationWidget();
//		navMenuActivityManager.setDisplay(navDisplay);
//		swipeMenu1.setMenuDisplay(navDisplay);
//		
//		RootPanel.get().add(swipeMenu1);
		
		RootPanel.get().add(swipeMenu);
		mGWTPlaceHistoryHandler.register(placeController, eventBus, new HomePlace());
		mGWTPlaceHistoryHandler.handleCurrentHistory();
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
