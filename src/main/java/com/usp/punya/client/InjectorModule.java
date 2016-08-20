package com.usp.punya.client;

import java.util.logging.Logger;

import javax.inject.Singleton;

import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Provides;
import com.googlecode.mgwt.mvp.client.AnimatingActivityManager;
import com.usp.punya.client.PunyaActivityMapper.ActivityFactory;

public class InjectorModule extends AbstractGinModule {
	@Override
	protected void configure() {
		bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);
		bind(com.googlecode.mgwt.mvp.client.AnimationMapper.class)
		.to(AnimationMapper.class).in(Singleton.class);
		bind(ActivityMapper.class).to(PunyaActivityMapper.class).in(Singleton.class);

		// View interfaces to their singleton Widgets
		// the Widgets themselves are set as singletons
		bind(SigninView.class).to(SigninWidget.class);

		// Place to Activity assisted injection
		install(new GinFactoryModuleBuilder().build(ActivityFactory.class));

	}

	@Singleton @Provides
	Logger getLogger() {
		return Logger.getLogger("Punya");
	}

	@Singleton
	@Provides
	PlaceController providePlaceController(EventBus eventBus) {
		return new PlaceController(eventBus);
	}

	@Singleton
	@Provides
	AnimatingActivityManager provideActivityManager(
			ActivityMapper mapper,
			AnimationMapper animationMapper,
			EventBus eventBus) {
		return new AnimatingActivityManager(mapper, animationMapper, eventBus);
	}
}
