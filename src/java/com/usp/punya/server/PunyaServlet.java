package com.usp.punya.server;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.usp.punya.server.backend.Book;
import com.usp.punya.server.backend.EMFService;

@SuppressWarnings("serial")
public class PunyaServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain");
		Book b = new Book();
		b.setId(123456L);
		EntityManager entityManager = EMFService.get().createEntityManager();
		entityManager.persist(b);
        entityManager.close();
		resp.getWriter().println("Hello, world");
	}
}
