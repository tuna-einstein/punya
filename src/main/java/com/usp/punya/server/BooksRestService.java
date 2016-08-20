package com.usp.punya.server;

import org.restlet.data.Form;
import org.restlet.resource.Put;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.Delete;
import org.restlet.resource.ServerResource;

import com.usp.punya.shared.model.Book;

import org.restlet.representation.Representation;

public class BooksRestService extends ServerResource {

	//private static final Logger logger = Logger.getLogger(BooksRestService.class);

	@Get
	public Book getSomething() {

		String request = getQuery().getValues("request");
		
		Book book = new Book();
		book.setTitle(request);

		return book;
	}

	@Post
	public String postSomething(Representation entity) {

		String request;
		
		Form form = new Form(entity);
		request = form.getValues("request");
//
//		if (logger.isDebugEnabled()) {
//			logger.debug("Start postSomething");
//			logger.debug("data: '" + request + "'");
//		}
		
		String result = null;
		result = "Response from Restlet Webservice : " + request;

//		if (logger.isDebugEnabled()) {
//			logger.debug("result: '" + result + "'");
//			logger.debug("End postSomething");
//		}
		return result;
	}

	@Put
	public String putSomething(Representation entity) {
		
		String request;
		
		Form form = new Form(entity);
		request = form.getValues("request");
		
//		if (logger.isDebugEnabled()) {
//			logger.debug("Start putSomething");
//			logger.debug("data: '" + request + "'");
//		}

		String result = null;
		result = "Response from Restlet Webservice" + request;
			
//
//		if (logger.isDebugEnabled()) {
//			logger.debug("result: '" + result + "'");
//			logger.debug("End putSomething");
//		}
		return result;
	}

	@Delete
	public void deleteSomething(Representation entity) {
		
		String request;
		
		Form form = new Form(entity);
		request = form.getValues("request");

//		if (logger.isDebugEnabled()) {
//			logger.debug("Start deleteSomething");
//			logger.debug("data: '" + request + "'");
//		}


//		if (logger.isDebugEnabled()) {
//			logger.debug("End deleteSomething");
//		}
	}
}
