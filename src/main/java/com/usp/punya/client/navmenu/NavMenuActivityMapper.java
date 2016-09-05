package com.usp.punya.client.navmenu;

import javax.inject.Inject;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.place.shared.Place;

public class NavMenuActivityMapper implements ActivityMapper{

	@Inject
	ActivityFactory factory;
	
	@Override
	public Activity getActivity(Place place) {
		GWT.log("NavMenuActivityMapper : " + place.getClass().getName());
		return factory.create();
	}
	
	/**
	 * Methods capable of creating Activity given the place that is passed in
	 *
	 */
	public interface ActivityFactory {
		MenuActivity create();
	}

}
