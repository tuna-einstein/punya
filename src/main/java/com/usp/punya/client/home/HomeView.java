package com.usp.punya.client.home;

import com.google.gwt.user.client.ui.IsWidget;

public interface HomeView extends IsWidget {
	
	public void setPresenter(Presenter presenter);
	
	public interface Presenter {
	
		public void onItemSelected(int index);

        public void onMenuButtonPressed();
		
	}
}
