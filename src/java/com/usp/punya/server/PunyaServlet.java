package com.usp.punya.server;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.usp.punya.server.backend.Book;
import com.usp.punya.server.backend.Currency;
import com.usp.punya.server.backend.EMFService;

@SuppressWarnings("serial")
public class PunyaServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain");
		Book b = new Book();
		b.setTitle("Bhagbat Gita");
		EntityManager entityManager = EMFService.get().createEntityManager();
		entityManager.persist(b);
        entityManager.close();
        
        entityManager = EMFService.get().createEntityManager();
        Currency c = new Currency();
		c.setDescription("Us Dollar");
		c.setType("USD");
		entityManager.persist(c);
		
		resp.getWriter().println("Hello1, world");
	}
}
