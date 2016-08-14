package com.usp.punya.server.backend;

import com.usp.punya.server.backend.EMF;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.datanucleus.query.JPACursorHelper;

import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Named;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityManager;
import javax.persistence.Query;

@Api(name = "currencyendpoint", namespace = @ApiNamespace(ownerDomain = "usp.com", ownerName = "usp.com", packagePath = "punya.server.backend"))
public class CurrencyEndpoint {

	/**
	 * This method lists all the entities inserted in datastore.
	 * It uses HTTP GET method and paging support.
	 *
	 * @return A CollectionResponse class containing the list of all entities
	 * persisted and a cursor to the next page.
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	@ApiMethod(name = "listCurrency")
	public CollectionResponse<Currency> listCurrency(@Nullable @Named("cursor") String cursorString,
			@Nullable @Named("limit") Integer limit) {

		EntityManager mgr = null;
		Cursor cursor = null;
		List<Currency> execute = null;

		try {
			mgr = getEntityManager();
			Query query = mgr.createQuery("select from Currency as Currency");
			if (cursorString != null && cursorString != "") {
				cursor = Cursor.fromWebSafeString(cursorString);
				query.setHint(JPACursorHelper.CURSOR_HINT, cursor);
			}

			if (limit != null) {
				query.setFirstResult(0);
				query.setMaxResults(limit);
			}

			execute = (List<Currency>) query.getResultList();
			cursor = JPACursorHelper.getCursor(execute);
			if (cursor != null)
				cursorString = cursor.toWebSafeString();

			// Tight loop for fetching all entities from datastore and accomodate
			// for lazy fetch.
			for (Currency obj : execute)
				;
		} finally {
			mgr.close();
		}

		return CollectionResponse.<Currency>builder().setItems(execute).setNextPageToken(cursorString).build();
	}

	/**
	 * This method gets the entity having primary key id. It uses HTTP GET method.
	 *
	 * @param id the primary key of the java bean.
	 * @return The entity with primary key id.
	 */
	@ApiMethod(name = "getCurrency")
	public Currency getCurrency(@Named("id") Long id) {
		EntityManager mgr = getEntityManager();
		Currency currency = null;
		try {
			currency = mgr.find(Currency.class, id);
		} finally {
			mgr.close();
		}
		return currency;
	}

	/**
	 * This inserts a new entity into App Engine datastore. If the entity already
	 * exists in the datastore, an exception is thrown.
	 * It uses HTTP POST method.
	 *
	 * @param currency the entity to be inserted.
	 * @return The inserted entity.
	 */
	@ApiMethod(name = "insertCurrency")
	public Currency insertCurrency(Currency currency) {
		EntityManager mgr = getEntityManager();
		try {
			if (containsCurrency(currency)) {
				throw new EntityExistsException("Object already exists");
			}
			mgr.persist(currency);
		} finally {
			mgr.close();
		}
		return currency;
	}

	/**
	 * This method is used for updating an existing entity. If the entity does not
	 * exist in the datastore, an exception is thrown.
	 * It uses HTTP PUT method.
	 *
	 * @param currency the entity to be updated.
	 * @return The updated entity.
	 */
	@ApiMethod(name = "updateCurrency")
	public Currency updateCurrency(Currency currency) {
		EntityManager mgr = getEntityManager();
		try {
			if (!containsCurrency(currency)) {
				throw new EntityNotFoundException("Object does not exist");
			}
			mgr.persist(currency);
		} finally {
			mgr.close();
		}
		return currency;
	}

	/**
	 * This method removes the entity with primary key id.
	 * It uses HTTP DELETE method.
	 *
	 * @param id the primary key of the entity to be deleted.
	 */
	@ApiMethod(name = "removeCurrency")
	public void removeCurrency(@Named("id") Long id) {
		EntityManager mgr = getEntityManager();
		try {
			Currency currency = mgr.find(Currency.class, id);
			mgr.remove(currency);
		} finally {
			mgr.close();
		}
	}

	private boolean containsCurrency(Currency currency) {
		if (currency.getCurrencyId() == null || currency.getCurrencyId() == 0) {
			return false;
		}
		EntityManager mgr = getEntityManager();
		boolean contains = true;
		try {
			Currency item = mgr.find(Currency.class, currency.getCurrencyId());
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
