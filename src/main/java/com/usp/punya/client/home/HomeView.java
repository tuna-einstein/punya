package com.usp.punya.client.home;

import java.util.List;

import com.google.gwt.user.client.ui.IsWidget;
import com.usp.punya.client.navmenu.MenuItem;

public interface HomeView extends IsWidget {
	
	public void setPresenter(Presenter presenter);
	
	public interface Presenter {
	
		public void onItemSelected(int index);

        public void onMenuButtonPressed();
		
	}
	
	public void render(List<MenuItem> createTopicsList);
	
	public void refresh();

}
