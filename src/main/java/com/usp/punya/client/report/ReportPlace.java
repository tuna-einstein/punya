package com.usp.punya.client.report;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class ReportPlace extends Place {
	
	@Prefix("report")
	public static class Tokenizer implements PlaceTokenizer<ReportPlace> {
		
		@Override
		public ReportPlace getPlace(String token) {
			return new ReportPlace();
		}
		
		@Override
		public String getToken(ReportPlace place) {
			return "report";
		}
	}
}
