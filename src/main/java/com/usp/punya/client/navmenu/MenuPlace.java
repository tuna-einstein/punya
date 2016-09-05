package com.usp.punya.client.navmenu;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class MenuPlace extends Place {

	public static class Tokenizer implements PlaceTokenizer<MenuPlace> {
		
		@Override
		public MenuPlace getPlace(String token) {
			return new MenuPlace();
		}
		
		@Override
		public String getToken(MenuPlace place) {
			return "";
		}
	}
}
