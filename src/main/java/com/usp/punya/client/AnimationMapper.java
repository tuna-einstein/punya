package com.usp.punya.client;

import com.google.gwt.place.shared.Place;
import com.googlecode.mgwt.ui.client.widget.animation.Animation;
import com.googlecode.mgwt.ui.client.widget.animation.Animations;
import com.usp.punya.client.home.HomePlace;
import com.usp.punya.client.report.ReportPlace;

public class AnimationMapper implements com.googlecode.mgwt.mvp.client.AnimationMapper {

	@Override
	public Animation getAnimation(Place oldPlace, Place newPlace) {
		if (oldPlace == null) {
			return Animations.FADE;
		}

		if (oldPlace instanceof ReportPlace && newPlace instanceof HomePlace) {
			return Animations.FADE_REVERSE;
		}

		return Animations.FADE;

	}
}
