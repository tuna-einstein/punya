package com.usp.punya.client.proxy;

import javax.inject.Inject;

import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.dispatcher.DispatcherFilter;

import com.google.gwt.http.client.RequestBuilder;
import com.google.inject.assistedinject.Assisted;

public class BasicAuthHeaderDispatcherFilter implements DispatcherFilter {
   
	private final String accessToken;
	
	@Inject
	BasicAuthHeaderDispatcherFilter(@Assisted String accessToken) {
		this.accessToken = accessToken;
	}

	@Override
	public boolean filter(Method method, RequestBuilder builder) {
		builder.setHeader("Authorization", "Bearer " + accessToken);
		return true;
	}

	public interface BasicAuthHeaderDispatcherFilterFactory {
		BasicAuthHeaderDispatcherFilter create(String accessToken);
	}
}
