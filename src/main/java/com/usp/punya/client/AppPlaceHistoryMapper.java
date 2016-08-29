package com.usp.punya.client;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;
import com.usp.punya.client.home.HomePlace;
import com.usp.punya.client.place.SigninPlace;
import com.usp.punya.client.report.ReportPlace;

@WithTokenizers({
	SigninPlace.Tokenizer.class,
	HomePlace.Tokenizer.class,
	ReportPlace.Tokenizer.class})
public interface AppPlaceHistoryMapper extends PlaceHistoryMapper {
}
