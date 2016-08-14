package com.usp.punya.server.backend;

import java.util.List;

import javax.annotation.Nullable;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.google.api.server.spi.auth.common.User;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.api.oauth.OAuthRequestException;
import com.google.appengine.datanucleus.query.JPACursorHelper;

@Api(name = "bookendpoint",
version = "v1",
scopes=Constants.EMAIL_SCOPE,
clientIds = {
		Constants.WEB_CLIENT_ID,
		com.google.api.server.spi.Constant.API_EXPLORER_CLIENT_ID
},
audiences = {
		Constants.ANDROID_AUDIENCE
},  
namespace = @ApiNamespace(ownerDomain = "usp.com", ownerName = "usp.com", packagePath = "book.entity"))
public class BookEndpoint {

	/**
	 * This method lists all the entities inserted in datastore. It uses HTTP
	 * GET method and paging support.
	 *
	 * @return A CollectionResponse class containing the list of all entities
	 *         persisted and a cursor to the next page.
	 * @throws OAuthRequestException 
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	@ApiMethod(name = "listBooks")
	public CollectionResponse<Book> listBook(
			@Nullable @Named("cursor") String cursorString,
			@Nullable @Named("limit") Integer limit,
			User user) throws OAuthRequestException {
		if (user == null) {
			throw new OAuthRequestException("Authentication required");
		}
		EntityManager mgr = null;
		Cursor cursor = null;
		List<Book> execute = null;

		try {
			mgr = getEntityManager();
			Query query = mgr.createQuery("SELECT b FROM Book b", Book.class);
			if (cursorString != null && cursorString != "") {
				cursor = Cursor.fromWebSafeString(cursorString);
				query.setHint(JPACursorHelper.CURSOR_HINT, cursor);
			}

			if (limit != null) {
				query.setMaxResults(limit);
			}

			execute = query.getResultList();
			if (limit != null && execute != null && execute.size() == limit) {
				cursor = JPACursorHelper.getCursor(execute);
				if (cursor != null) {
					cursorString = cursor.toWebSafeString();
					// store cursorString for later use
				}
			}
		} finally {
			mgr.close();
		}

		return CollectionResponse.<Book> builder().setItems(execute).setNextPageToken(cursorString).build();
	}

	/**
	 * This method gets the entity having primary key id. It uses HTTP GET
	 * method.
	 *
	 * @param id
	 *            the primary key of the java bean.
	 * @return The entity with primary key id.
	 * @throws OAuthRequestException 
	 */
	@ApiMethod(name = "getBook")
	public Book getBook(@Named("id") Long id, User user) throws OAuthRequestException {
		if (user == null) {
			throw new OAuthRequestException("Authentication required");
		}
		EntityManager mgr = getEntityManager();
		Book book = null;
		try {
			book = mgr.find(Book.class, id);
		} finally {
			mgr.close();
		}
		return book;
	}

	/**
	 * This inserts a new entity into App Engine datastore. If the entity
	 * already exists in the datastore, an exception is thrown. It uses HTTP
	 * POST method.
	 *
	 * @param book
	 *            the entity to be inserted.
	 * @return The inserted entity.
	 * @throws OAuthRequestException 
	 */
	@ApiMethod(name = "insertBook")
	public Book insertBook(Book book, User user) throws OAuthRequestException {
		if (user == null) {
			throw new OAuthRequestException("Authentication required");
		}
		EntityManager mgr = getEntityManager();
		try {
			if (book.getId() != null) {
				if (containsBook(book)) {
					throw new EntityExistsException("Object already exists");
				}
			}
		//	mgr.getTransaction().begin();
			mgr.persist(book);
		//	mgr.getTransaction().commit();
		} finally {
			mgr.close();
		}
		return book;
	}

	/**
	 * This method removes the entity with primary key id. It uses HTTP DELETE
	 * method.
	 *
	 * @param id
	 *            the primary key of the entity to be deleted.
	 * @throws OAuthRequestException 
	 */
	@ApiMethod(name = "removeBook")
	public void removeBook(@Named("id") Long id, User user) throws OAuthRequestException {
		if (user == null) {
			throw new OAuthRequestException("Authentication required");
		}
		EntityManager mgr = getEntityManager();
		try {
			Book book = mgr.find(Book.class, id);
			if (book != null) {
				mgr.getTransaction().begin();
				mgr.remove(book);
				mgr.getTransaction().commit();
			}
		} finally {
			mgr.close();
		}
	}

	private boolean containsBook(Book book) {
		EntityManager mgr = getEntityManager();
		try {
			Book result = mgr.find(Book.class, book.getId());
			return result != null;
		} finally {
			mgr.close();
		}
	}

	private static EntityManager getEntityManager() {
		return EMFService.get().createEntityManager();
	}

}
