package com.usp.punya.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class ReportFormPlace extends Place {
	
	@Prefix("report")
	public static class Tokenizer implements PlaceTokenizer<ReportFormPlace> {
		
		@Override
		public ReportFormPlace getPlace(String token) {
			return new ReportFormPlace();
		}
		
		@Override
		public String getToken(ReportFormPlace place) {
			return "report";
		}
	}
}

