package com.usp.punya.client.proxy;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import com.usp.punya.shared.model.Book;

public interface BookService extends RestService {
	
    @GET
    @Path("/bookendpoint/v1/book")
    public void listBooks(MethodCallback<List<Book>> callback);
}
