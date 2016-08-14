package com.usp.punya.server.backend;

import com.usp.punya.server.backend.EMF;
import com.usp.punya.server.backend.model.BookInfo;
import com.google.api.server.spi.BackendProperties;
import com.google.api.server.spi.auth.common.User;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.api.oauth.OAuthRequestException;
import com.google.appengine.datanucleus.query.JPACursorHelper;

import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Named;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityManager;
import javax.persistence.Query;

@Api(name = "reportendpoint", namespace = @ApiNamespace(ownerDomain = "usp.com", ownerName = "usp.com", packagePath = "punya.server.backend"))
public class ReportEndpoint {

	/**
	 * This method lists all the entities inserted in datastore.
	 * It uses HTTP GET method and paging support.
	 *
	 * @return A CollectionResponse class containing the list of all entities
	 * persisted and a cursor to the next page.
	 */
	@ApiMethod(name = "listReport")
	public CollectionResponse<Report> listReport(@Nullable @Named("cursor") String cursorString,
			@Nullable @Named("limit") Integer limit) {

		EntityManager mgr = null;
		Cursor cursor = null;
		List<Report> execute = null;

		try {
			mgr = getEntityManager();
			Query query = mgr.createQuery("select from Report as Report");
			if (cursorString != null && cursorString != "") {
				cursor = Cursor.fromWebSafeString(cursorString);
				query.setHint(JPACursorHelper.CURSOR_HINT, cursor);
			}

			if (limit != null) {
				query.setFirstResult(0);
				query.setMaxResults(limit);
			}

			execute = (List<Report>) query.getResultList();
			cursor = JPACursorHelper.getCursor(execute);
			if (cursor != null)
				cursorString = cursor.toWebSafeString();

			// Tight loop for fetching all entities from datastore and accomodate
			// for lazy fetch.
			for (Report obj : execute)
				;
		} finally {
			mgr.close();
		}

		return CollectionResponse.<Report>builder().setItems(execute).setNextPageToken(cursorString).build();
	}

	/**
	 * This method gets the entity having primary key id. It uses HTTP GET method.
	 *
	 * @param id the primary key of the java bean.
	 * @return The entity with primary key id.
	 * @throws OAuthRequestException 
	 */
	@ApiMethod(name = "getReport")
	public Report getReport(@Named("id") Long id, User user) throws OAuthRequestException {
		EntityManager mgr = getEntityManager();
		BookEndpoint bookEndpoint = new BookEndpoint();
		Report report = null;
		try {
			report = mgr.find(Report.class, id);
			for (BookInfo bookInfo : report.getBookInfos()) {
				bookInfo.setBook(bookEndpoint.getBook(bookInfo.getBookId(), user));
			}
		} finally {
			mgr.close();
		}
		return report;
	}

	/**
	 * This inserts a new entity into App Engine datastore. If the entity already
	 * exists in the datastore, an exception is thrown.
	 * It uses HTTP POST method.
	 *
	 * @param report the entity to be inserted.
	 * @return The inserted entity.
	 * @throws OAuthRequestException 
	 */
	@ApiMethod(name = "insertReport")
	public Report insertReport(Report report, User user) throws OAuthRequestException {
		EntityManager mgr = getEntityManager();
		BookEndpoint bookEndpoint = new BookEndpoint();
		try {
//			if (containsReport(report)) {
//				throw new EntityExistsException("Object already exists");
//			}
//			for (BookInfo bookInfo : report.getBookInfos()) {
//				bookInfo.setBook(bookEndpoint.getBook(bookInfo.getBookId(), user));
//			}
			mgr.persist(report);
		} finally {
			mgr.close();
		}
		return report;
	}

	/**
	 * This method is used for updating an existing entity. If the entity does not
	 * exist in the datastore, an exception is thrown.
	 * It uses HTTP PUT method.
	 *
	 * @param report the entity to be updated.
	 * @return The updated entity.
	 */
	@ApiMethod(name = "updateReport")
	public Report updateReport(Report report) {
		EntityManager mgr = getEntityManager();
		try {
			if (!containsReport(report)) {
				throw new EntityNotFoundException("Object does not exist");
			}
			mgr.persist(report);
		} finally {
			mgr.close();
		}
		return report;
	}

	/**
	 * This method removes the entity with primary key id.
	 * It uses HTTP DELETE method.
	 *
	 * @param id the primary key of the entity to be deleted.
	 */
	@ApiMethod(name = "removeReport")
	public void removeReport(@Named("id") Long id) {
		EntityManager mgr = getEntityManager();
		try {
			Report report = mgr.find(Report.class, id);
			mgr.remove(report);
		} finally {
			mgr.close();
		}
	}

	private boolean containsReport(Report report) {
		EntityManager mgr = getEntityManager();
		boolean contains = true;
		try {
			Report item = mgr.find(Report.class, report.getId());
			if (item == null) {
				contains = false;
			}
		} finally {
			mgr.close();
		}
		return contains;
	}

	private static EntityManager getEntityManager() {
		return EMF.get().createEntityManager();
	}

}
