package com.usp.punya.client;

import javax.inject.Inject;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.usp.punya.client.activity.EditReportActivity;
import com.usp.punya.client.activity.SignInActivity;
import com.usp.punya.client.place.ReportFormPlace;
import com.usp.punya.client.place.SigninPlace;

public class PunyaActivityMapper implements ActivityMapper {

	@Inject
	ActivityFactory factory;
	
	@Override
	public Activity getActivity(Place place) {
		if (place instanceof SigninPlace) {
			return factory.createSigninActivity(((SigninPlace) place));
		}
		if (place instanceof ReportFormPlace) {
			return factory.createEditReportActivity((ReportFormPlace) place);
		}
		return null;
	}
	
	/**
	 * Methods capable of creating Activity given the place that is passed in
	 *
	 */
	public interface ActivityFactory {
		SignInActivity createSigninActivity(SigninPlace place);
		EditReportActivity createEditReportActivity(ReportFormPlace place);
	}
}