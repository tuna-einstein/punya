package com.usp.punya.server;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.engine.Engine;
import org.restlet.routing.Router;
//import org.restlet.ext.jackson.JacksonConverter;

public class BooksRestServiceApplication extends Application {

	@Override
	public synchronized Restlet createInboundRoot() {
		
		//Engine.getInstance().getRegisteredConverters().add(new JacksonConverter());
		// Create a router Restlet that routes each call to a
		Router router = new Router(getContext());
		router.attach("/BooksRestService", BooksRestService.class);
		router.attach("/BooksRestService/{request}", BooksRestService.class);
		return router;
	}
}
