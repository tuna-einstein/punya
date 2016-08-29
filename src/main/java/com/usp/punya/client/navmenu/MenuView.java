package com.usp.punya.client.navmenu;

import java.util.List;

import com.google.gwt.user.client.ui.IsWidget;

public interface MenuView extends IsWidget {

	public void setPresenter(Presenter presenter);
	
	public void render(List<MenuItem> createTopicsList);

	public void setSelected(int lastIndex, boolean b);

	public interface Presenter {

		public void onAboutButtonPressed();

		public void onItemSelected(int index);

	}
}