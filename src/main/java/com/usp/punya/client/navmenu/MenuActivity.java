package com.usp.punya.client.navmenu;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.event.shared.EventBus;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;
import com.googlecode.mgwt.ui.client.widget.menu.swipe.SwipeMenu;

public class MenuActivity extends MGWTAbstractActivity implements MenuView.Presenter {

	@Inject MenuView view;
	@Inject SwipeMenu swipeMenu;

	@Override
	public void start(AcceptsOneWidget panel, final EventBus eventBus) {
		view.setPresenter(this);
		view.render(createTopicsList());
		panel.setWidget(view);
		GWT.log("start MenuActivity");
	}

	@Override
	public void onStop() {
		view.setPresenter(null);
	}

	@Override
	public void onItemSelected(int index) {
		if (index == 0) {
			swipeMenu.close(false);
			//clientFactory.getPlaceController().goTo(new MenuItemOnePlace());
			return;
		}
		if (index == 1) {
			swipeMenu.close(false);
			//clientFactory.getPlaceController().goTo(new MenuItemTwoPlace());
			return;
		}
		if (index == 2) {
			swipeMenu.close(false);
			//clientFactory.getPlaceController().goTo(new MenuItemThreePlace());
			return;
		}		
	}

	@Override
	public void onAboutButtonPressed() {
		swipeMenu.close(false);
		//clientFactory.getPlaceController().goTo(new AboutPlace());
	}

	private List<MenuItem> createTopicsList() {
		ArrayList<MenuItem> list = new ArrayList<MenuItem>();

		list.add(new MenuItem("Menu Item One"));
		list.add(new MenuItem("Menu Item Two"));
		list.add(new MenuItem("Menu Item Three"));

		return list;
	}
}