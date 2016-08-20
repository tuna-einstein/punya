package com.usp.punya.client;

import com.google.gwt.place.shared.Place;
import com.googlecode.mgwt.ui.client.widget.animation.Animation;
import com.googlecode.mgwt.ui.client.widget.animation.Animations;

public class AnimationMapper implements com.googlecode.mgwt.mvp.client.AnimationMapper {

	@Override
	public Animation getAnimation(Place oldPlace, Place newPlace) {
		if ( oldPlace == null ) {
			return null;
		} else {
			return Animations.SLIDE;
		}
	}
	
}
