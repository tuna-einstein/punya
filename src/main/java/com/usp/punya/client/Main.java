package com.usp.punya.client;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.gargoylesoftware.htmlunit.javascript.host.Console;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;
import com.googlecode.mgwt.mvp.client.AnimatingActivityManager;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.MGWTSettings;
import com.googlecode.mgwt.ui.client.animation.AnimationHelper;
import com.googlecode.mgwt.ui.client.widget.animation.AnimatableDisplay;
import com.googlecode.mgwt.ui.client.widget.animation.AnimationWidget;
import com.googlecode.mgwt.ui.client.widget.animation.Animations;
import com.googlecode.mgwt.ui.client.widget.form.Form;
import com.googlecode.mgwt.ui.client.widget.input.listbox.MListBox;
import com.googlecode.mgwt.ui.client.widget.panel.flex.RootFlexPanel;

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

//
//		// build animation helper and attach it
//		AnimationHelper animationHelper = new AnimationHelper();
//		RootPanel.get().add(animationHelper);
//
//		//	    // build some UI
//		//	    RootFlexPanel rootFlexPanel = new RootFlexPanel();
//		//	    Button button = new Button("Hello mgwt");
//		//	    MListBox box = new MListBox();
//		//	    box.addItem("Hello");
//		//	    box.addItem("Hii");
//		//	    
//		////	    Form form = new Form();
//		////	    form.add();;
//		////	    form.add(box);
//		//	    
//		//	    rootFlexPanel.add();
//		// animate
//		animationHelper.goTo(new ReportView(), Animations.SLIDE);

	}
}
