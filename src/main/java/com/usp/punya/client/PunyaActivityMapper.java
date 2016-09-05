package com.usp.punya.client;

import javax.inject.Inject;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.place.shared.Place;
import com.usp.punya.client.home.HomeActivity;
import com.usp.punya.client.home.HomePlace;
import com.usp.punya.client.place.SigninPlace;
import com.usp.punya.client.report.ReportActivity;
import com.usp.punya.client.report.ReportPlace;
import com.usp.punya.client.signin.SignInActivity;

public class PunyaActivityMapper implements ActivityMapper {

	@Inject
	ActivityFactory factory;
	
	@Override
	public Activity getActivity(Place place) {
		GWT.log("PunyaActivityMapper:" + place.getClass().getName());
		if (place instanceof SigninPlace) {
			return factory.createSigninActivity(((SigninPlace) place));
		}
		if (place instanceof HomePlace) {
			return factory.create((HomePlace) place);
		}
		if (place instanceof ReportPlace) {
			return factory.create((ReportPlace) place);
		}
		return null;
	}
	
	/**
	 * Methods capable of creating Activity given the place that is passed in
	 *
	 */
	public interface ActivityFactory {
		SignInActivity createSigninActivity(SigninPlace place);
		HomeActivity create(HomePlace homePlace);
		ReportActivity create(ReportPlace reportPlace);
	}
}