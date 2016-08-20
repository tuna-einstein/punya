package com.usp.punya.client;

import javax.inject.Inject;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

public class PunyaActivityMapper implements ActivityMapper {

	@Inject
	ActivityFactory factory;
	
	@Override
	public Activity getActivity(Place place) {
		if (place instanceof SigninPlace) {
			return factory.createSigninActivity(((SigninPlace) place));
		}
		return null;
	}
	
	/**
	 * Methods capable of creating Activity given the place that is passed in
	 *
	 */
	public interface ActivityFactory {
		SignInActivity createSigninActivity(SigninPlace place);
	}
}