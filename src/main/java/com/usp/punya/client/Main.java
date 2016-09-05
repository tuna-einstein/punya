package com.usp.punya.client;

import java.util.Arrays;
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
import com.googlecode.mgwt.ui.client.widget.animation.AnimationWidget;
import com.googlecode.mgwt.ui.client.widget.menu.swipe.SwipeMenu;
import com.usp.punya.client.home.HomePlace;
import com.usp.punya.client.navmenu.MenuPlace;
import com.usp.punya.client.navmenu.MenuView;
import com.usp.punya.client.navmenu.NavMenu;
import com.usp.punya.client.proxy.BookService;
import com.usp.punya.shared.model.Book;
import com.vaadin.polymer.Polymer;
import com.vaadin.polymer.elemental.Function;
import com.vaadin.polymer.paper.widget.PaperButton;

public class Main implements EntryPoint {
			
	@Inject Logger logger;
	@Inject AnimatingActivityManager animatingActivityManager;
	@Inject PlaceController placeController;
	@Inject AnimatingActivityManager activityManager;
	@Inject @NavMenu AnimatingActivityManager navMenuActivityManager;
	@Inject SwipeMenu swipeMenu;
	@Inject MGWTPlaceHistoryHandler mGWTPlaceHistoryHandler;
	@Inject EventBus eventBus;
	@Inject MenuView menuView;
	
	public void onModuleLoad() {
		
		
		//Polymer.startLoading();
		

        // Although gwt-polymer-elements takes care of dynamic loading of components
        // when they are created using Polymer.createElement or we use Polymer Widgets,
        // there are certain features which must be loaded previously to start
        // the application. Hence you have to add import tags to your host page or
        // import them dynamically, in this case it might be necessary to wait until
        // the components are ready.

        // The `Polymer` utility class provide a set of methods for facilitating it,
        // you can pass tag-names for standard component locations (tag-name/tag-name.html)
        // or relative urls otherwise.
        Polymer.importHref(Arrays.asList(
                // Paper applications must always import paper-styles
                "paper-styles",
                // Styles for paper examples
                "paper-styles/demo-pages.html",
                // Iconsets must be loaded before using any component depending on them.
                // We load all Iron and Vaadin collections.
                "iron-icons",
                "iron-icons/communication-icons.html",
                "iron-icons/av-icons.html",
                "iron-icons/device-icons.html",
                "iron-icons/editor-icons.html",
                "iron-icons/hardware-icons.html",
                "iron-icons/image-icons.html",
                "iron-icons/maps-icons.html",
                "iron-icons/notification-icons.html",
                "iron-icons/social-icons.html",
                "vaadin-icons",
                // Flex-layout and animations are used in all the app, loading it early.
                "iron-flex-layout",
                "neon-animation"
                ));

        Polymer.whenReady(new Function() {
			@Override
			public Object call(Object o) {
				Injector.INSTANCE.injectEntryPoint(Main.this);
				MGWT.applySettings(MGWTSettings.getAppSetting());
				mGWTPlaceHistoryHandler.register(placeController, eventBus, new HomePlace());
				prepareDisplay();
				mGWTPlaceHistoryHandler.handleCurrentHistory();
			    return null;
			}
		});
	}
	
	private void prepareDisplay() {
		AnimationWidget navDisplay = new AnimationWidget();
		navMenuActivityManager.setDisplay(navDisplay);
		swipeMenu.setMenuDisplay(navDisplay);
		
		AnimationWidget mainDisplay = new AnimationWidget();
		activityManager.setDisplay(mainDisplay);
		swipeMenu.setContentDisplay(mainDisplay);
		
		
		
		RootPanel.get().add(swipeMenu);
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
