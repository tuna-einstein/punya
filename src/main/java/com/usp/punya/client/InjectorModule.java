package com.usp.punya.client;

import java.util.logging.Logger;

import javax.inject.Singleton;

import org.fusesource.restygwt.client.dispatcher.DefaultFilterawareDispatcher;
import org.fusesource.restygwt.client.dispatcher.FilterawareDispatcher;

import com.google.api.gwt.oauth2.client.AuthRequest;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Provides;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.googlecode.mgwt.mvp.client.AnimatingActivityManager;
import com.googlecode.mgwt.mvp.client.history.MGWTPlaceHistoryHandler;
import com.usp.punya.client.PunyaActivityMapper.ActivityFactory;
import com.usp.punya.client.home.HomeView;
import com.usp.punya.client.home.HomeViewGwtImpl;
import com.usp.punya.client.proxy.BasicAuthHeaderDispatcherFilter.BasicAuthHeaderDispatcherFilterFactory;
import com.usp.punya.client.report.ReportView;
import com.usp.punya.client.report.ReportViewGwtImpl;
import com.usp.punya.client.signin.SigninView;
import com.usp.punya.client.signin.SigninViewGwtImpl;

public class InjectorModule extends AbstractGinModule {

	String AUTH_URL = "https://accounts.google.com/o/oauth2/auth";
	String CLIENT_ID = "816356369753-2fk8iao9pvdqbl4inor9qanfuuuimcbp.apps.googleusercontent.com";

	@Override
	protected void configure() {
		bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);
		bind(com.googlecode.mgwt.mvp.client.AnimationMapper.class)
		.to(AnimationMapper.class).in(Singleton.class);
		bind(ActivityMapper.class).to(PunyaActivityMapper.class).in(Singleton.class);

		// View interfaces to their singleton Widgets
		// the Widgets themselves are set as singletons
		bind(HomeView.class).to(HomeViewGwtImpl.class);
		bind(ReportView.class).to(ReportViewGwtImpl.class);
		bind(SigninView.class).to(SigninViewGwtImpl.class);

		// Place to Activity assisted injection
		install(new GinFactoryModuleBuilder().build(ActivityFactory.class));
		install(new GinFactoryModuleBuilder().build(
				BasicAuthHeaderDispatcherFilterFactory.class));
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
	
	@Provides
	@Singleton
	public FilterawareDispatcher provideDispatcher() {
		return new DefaultFilterawareDispatcher();
	}

	@Provides
	@Singleton
	public AuthRequest getAuthRequest() {
		return new AuthRequest(AUTH_URL, CLIENT_ID)
				.withScopes("https://www.googleapis.com/auth/userinfo.email");
	}
	
	@Singleton
	@Provides
	AppHistoryObserver provideAppHistoryObserver() {
		return new AppHistoryObserver();
	}
	
	@Singleton
	@Provides
	AppPlaceHistoryMapper provideAppPlaceHistoryMapper() {
		return GWT.create(AppPlaceHistoryMapper.class);
	}
	
	@Singleton
	@Provides
	MGWTPlaceHistoryHandler provideMGWTPlaceHistoryHandler(
			AppHistoryObserver appHistoryObserver,
			AppPlaceHistoryMapper appPlaceHistoryMapper) {
		return new MGWTPlaceHistoryHandler(appPlaceHistoryMapper, appHistoryObserver);
	}
}
