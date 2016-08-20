package com.usp.punya.shared.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.google.appengine.api.datastore.Key;

@Entity
public class BookInfo {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Key id;

	@Transient
	private Book book;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Report report;
	
	private Long count;

	private long bookId;
	
	
	public Key getId() {
		return id;
	}
	
	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public long getBookId() {
		return bookId;
	}

	public void setBookId(long bookId) {
		this.bookId = bookId;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}
}
