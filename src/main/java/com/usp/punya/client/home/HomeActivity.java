package com.usp.punya.client.home;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;

import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.event.shared.EventBus;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;
import com.googlecode.mgwt.ui.client.widget.menu.swipe.SwipeMenu;
import com.usp.punya.client.navmenu.MenuItem;
import com.usp.punya.client.report.ReportPlace;

public class HomeActivity extends MGWTAbstractActivity implements HomeView.Presenter {

	@Inject HomeView view;
	@Inject Provider<SwipeMenu> swipeMenu;
	@Inject PlaceController placeController;

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		view.setPresenter(this);
		view.render(createTopicsList());
		view.refresh();
		
		panel.setWidget(view);
	}

    private List<MenuItem> createTopicsList() {
        ArrayList<MenuItem> list = new ArrayList<MenuItem>();
        
        list.add(new MenuItem("Sample Activity"));
        
        return list;
    }

    @Override
    public void onItemSelected(int index) {
        if (index == 0) {
        	swipeMenu.get().close();
        	placeController.goTo(new ReportPlace());
        }        
    }

    @Override
    public void onMenuButtonPressed() {
    	if (swipeMenu.get().isOpen()) {
    		swipeMenu.get().close();
    	} else {
    		swipeMenu.get().open();
    	}
    }
}