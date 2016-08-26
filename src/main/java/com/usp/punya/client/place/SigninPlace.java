package com.usp.punya.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class SigninPlace extends Place {
	
	@Prefix("signin")
	public static class Tokenizer implements PlaceTokenizer<SigninPlace> {
		
		@Override
		public SigninPlace getPlace(String token) {
			return new SigninPlace();
		}
		
		@Override
		public String getToken(SigninPlace place) {
			return "signin";
		}
	}
}
