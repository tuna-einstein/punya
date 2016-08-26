package com.usp.punya.client.activity;

import javax.inject.Inject;

import org.fusesource.restygwt.client.Defaults;
import org.fusesource.restygwt.client.Resource;
import org.fusesource.restygwt.client.dispatcher.FilterawareDispatcher;

import com.google.api.gwt.oauth2.client.Auth;
import com.google.api.gwt.oauth2.client.AuthRequest;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.usp.punya.client.ErrorDialog;
import com.usp.punya.client.place.ReportFormPlace;
import com.usp.punya.client.proxy.BasicAuthHeaderDispatcherFilter.BasicAuthHeaderDispatcherFilterFactory;
import com.usp.punya.client.view.SigninView;
import com.usp.punya.client.view.SigninView.Bridge;
import com.usp.punya.client.proxy.ForbiddenDispatcherFilter;

public class SignInActivity extends AbstractActivity implements Bridge {

	@Inject SigninView view;
	@Inject AuthRequest authRequest;
	@Inject ErrorDialog errorDialog;
	@Inject FilterawareDispatcher filterawareDispatcher;
	@Inject BasicAuthHeaderDispatcherFilterFactory basicAuthHeaderDispatcherFilterFactory;
	@Inject ForbiddenDispatcherFilter forbiddenDispatcherFilter;
	@Inject PlaceController placeController;

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view);
		view.setBridge(this);
	}

	@Override
	public void onSigninClick() {
		Auth.get().login(authRequest, new Callback<String, Throwable>() {

			@Override
			public void onSuccess(String accessToken) {
				initRestyGwt(accessToken);
				placeController.goTo(new ReportFormPlace());
			}

			@Override
			public void onFailure(Throwable t) {
				errorDialog.showError(t);
			}
		});
	}
	
	private void initRestyGwt(String accessToken) {
		Defaults.setServiceRoot(
				new Resource(GWT.getModuleBaseURL()).resolve("../_ah/api").getUri());
		filterawareDispatcher.addFilter(forbiddenDispatcherFilter);
		filterawareDispatcher.addFilter(
				basicAuthHeaderDispatcherFilterFactory.create(accessToken));
		Defaults.setDispatcher(filterawareDispatcher);
	}
}
