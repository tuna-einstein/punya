package com.usp.punya.client.proxy;

import javax.inject.Inject;

import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.callback.DefaultFilterawareRequestCallback;
import org.fusesource.restygwt.client.dispatcher.DispatcherFilter;

import com.google.api.gwt.oauth2.client.Auth;
import com.google.api.gwt.oauth2.client.AuthRequest;
import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.Window;

public class ForbiddenDispatcherFilter implements DispatcherFilter {

	private final AuthRequest authRequest;
	@Inject
	ForbiddenDispatcherFilter(AuthRequest authRequest) {
		this.authRequest = authRequest;
	}

	@Override
	public boolean filter(Method method, RequestBuilder builder) {
		GWT.log("--> ForbiddenDispatcherFilter -> filter -> setCallback");
		builder.setCallback(new ForbiddenDispatcherCallback(method, authRequest));
		return true;
	}

	private static class ForbiddenDispatcherCallback extends DefaultFilterawareRequestCallback {

		private final AuthRequest authRequest;
		
		public ForbiddenDispatcherCallback(Method method, AuthRequest authRequest) {
	        super(method);
	        this.authRequest = authRequest;
	    }

		@Override
		public void onError(Request request, Throwable exception) {
			GWT.log("ForbiddenDispatcherCallback -> onError");
			requestCallback.onError(request, exception);
		}

		@Override
		public void onResponseReceived(Request request, Response response) {
			GWT.log("onResponseReceived");
			GWT.log(response.getStatusText() + response.getStatusCode());
			if (response.getStatusCode() == Response.SC_FORBIDDEN
					|| response.getStatusCode() == Response.SC_UNAUTHORIZED) {
				refreshAuthToken();
				
			} else {
				requestCallback.onResponseReceived(request, response);
			}
		}
		
		private void refreshAuthToken() {
			GWT.log("refreshAuthToken");
			Auth.get().login(authRequest, new Callback<String, Throwable>() {
				
				@Override
				public void onSuccess(String accessToken) {
					method.builder.setHeader("Authorization", "Bearer " + accessToken);
					try {
						method.builder.send();
					} catch (RequestException e) {
						GWT.log("Failed", e);
						showErrorDialog();
					}
				}
				
				@Override
				public void onFailure(Throwable e) {
					GWT.log("Failed", e);
					showErrorDialog();
				}
			});
		}
		
		private void showErrorDialog() {
			 // got no callback - well, goodbye
            if (Window.confirm("something severly went wrong - error - reload page ?")) {
                // Super severe error.
                // reload app or redirect.
                // ===> this breaks the app but that's by intention.
                Window.Location.reload();
            }
		}
	}
}
